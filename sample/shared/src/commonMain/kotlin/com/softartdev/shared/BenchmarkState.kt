package com.softartdev.shared

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.milliseconds

object BenchmarkState {
    val n: MutableState<Int> = mutableStateOf(value = 100)
    val tasks: SnapshotStateList<BenchmarkTask> = mutableStateListOf()
    var parallelDispatcher: CloseableCoroutineDispatcher = newFixedThreadPoolContext(n.value, "Benchmark")
    val showLoading: State<Boolean> = derivedStateOf { tasks.any { it.percent.value < 100 } }

    fun runTasks(coroutineContext: CoroutineContext) {
        if (tasks.isNotEmpty()) return
        parallelDispatcher = newFixedThreadPoolContext(n.value, "Benchmarks-${n.value}")
        for (i in 0 until n.value) {
            val task = BenchmarkTask(id = i)
            tasks.add(task)
        }
        tasks.forEach { parallelDispatcher.dispatch(coroutineContext, it.benchmarkRunnable) }
    }

    fun release() {
        parallelDispatcher.close()
        tasks.clear()
    }

    data class BenchmarkTask(
        val id: Int = tasks.size,
        val percent: MutableState<Int> = mutableStateOf(value = 0),
        val benchmarkRunnable: Runnable = Runnable {
            runBlocking {
                while (percent.value < 100) {
                    percent.value += 1
                    delay(id.milliseconds)//TODO payload
                }
            }
        },
    )
}