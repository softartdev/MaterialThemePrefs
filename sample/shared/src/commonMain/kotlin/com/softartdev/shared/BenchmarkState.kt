package com.softartdev.shared

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

expect val ioDispatcher: CoroutineDispatcher // on iOS is Default

enum class DispatcherType { IO, Default, Main, Unconfined, FixedN }

object BenchmarkState {
    val n: MutableState<Int> = mutableStateOf(value = 100)
    val tasks: SnapshotStateList<BenchmarkTask> = mutableStateListOf()
    var dispatcherType: MutableState<DispatcherType> = mutableStateOf(value = DispatcherType.IO)
    val showLoading: State<Boolean> = derivedStateOf { tasks.any { it.percent.value < 100 } }

    val parallelDispatcher: CoroutineDispatcher
        get() = when (dispatcherType.value) {
            DispatcherType.IO -> ioDispatcher
            DispatcherType.Default -> Dispatchers.Default
            DispatcherType.Main -> Dispatchers.Main
            DispatcherType.Unconfined -> Dispatchers.Unconfined
            DispatcherType.FixedN -> newFixedThreadPoolContext(n.value, "FixedThreadPool")
        }
    var supervisorJob: CompletableJob = SupervisorJob()
    var coroutineScope: CoroutineScope = CoroutineScope(parallelDispatcher + supervisorJob)

    fun release() {
        coroutineScope.cancel()
        tasks.clear()
    }

    // coroutines parallel execution with dispatcher
    fun runTasks() {
        if (tasks.isNotEmpty()) {
            release()
        }
        for (i in 0 until n.value) {
            val task = BenchmarkTask(id = i)
            tasks.add(task)
        }
        supervisorJob = SupervisorJob()
        coroutineScope = CoroutineScope(parallelDispatcher + supervisorJob)
        tasks.forEach {
            val job = coroutineScope.launch {
                it.percent.value = 0
                it.benchmarkRunnable.run()
            }
            it.job = job
        }
    }

    data class BenchmarkTask(
        val id: Int = tasks.size,
        val percent: MutableState<Int> = mutableStateOf(value = 0),
        val benchmarkRunnable: Runnable = Runnable {
            runBlocking {
                do {
                    while (percent.value < 100) {
                        percent.value += 1
                        delay(id.milliseconds)//TODO payload
                    }
                    percent.value = 0
                } while (percent.value < 100)
            }
        },
        var job: Job? = null
    )
}