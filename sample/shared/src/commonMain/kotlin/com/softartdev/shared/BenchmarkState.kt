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
    val showLoading: State<Boolean> = derivedStateOf { tasks.any(::isWorking) }
    private fun isWorking(task: BenchmarkTask): Boolean = task.job?.isActive ?: false

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

    // coroutines parallel execution with dispatcher
    fun runTasks() {
        if (tasks.isNotEmpty()) {
            release()
        }
        supervisorJob = SupervisorJob()
        coroutineScope = CoroutineScope(parallelDispatcher + supervisorJob)
        for (i in 0 until n.value) {
            val task = BenchmarkTask(id = i)
            task.job = coroutineScope.launch {
                task.benchmarkRunnable.run()
            }
            tasks.add(task)
        }
    }

    fun release() {
        coroutineScope.cancel()
        tasks.clear()
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