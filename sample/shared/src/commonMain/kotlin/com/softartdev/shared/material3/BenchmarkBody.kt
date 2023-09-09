@file:OptIn(ExperimentalMaterial3Api::class)

package com.softartdev.shared.material3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.softartdev.shared.AppState
import com.softartdev.shared.BenchmarkState
import com.softartdev.theme.pref.LocalThemePrefs
import kotlinx.coroutines.CoroutineScope
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class, ExperimentalLayoutApi::class)
@HiddenFromObjC
@Composable
fun BenchmarkBody(
    onBackClick: () -> Unit = { AppState.screenState.value = AppState.Screen.Settings },
    showLoading: State<Boolean> = BenchmarkState.showLoading,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "Benchmark", maxLines = 1) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = barActions()
        )
    }) { paddingValues ->
    Box(modifier = Modifier.padding(paddingValues)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (showLoading.value) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.Center
            ) {
                SuggestionChip({}, { Text("n = ${BenchmarkState.n.value}") }, enabled = false)
                SuggestionChip({ BenchmarkState.n.value += 1 }, { Text("+") })
                SuggestionChip({ BenchmarkState.n.value -= 1 }, { Text("-") })
                SuggestionChip(
                    onClick = { BenchmarkState.runTasks(coroutineContext = coroutineScope.coroutineContext) },
                    label = { Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null) },
                    enabled = !showLoading.value
                )
                SuggestionChip(onClick = BenchmarkState::release, { Icon(Icons.Default.Delete, null) })
            }
            LazyColumn {
                items(
                    count = BenchmarkState.tasks.size,
                    key = { index -> BenchmarkState.tasks[index].id },
                    itemContent = { index ->
                        val percent: Int by remember(
                            key1 = BenchmarkState.tasks[index].id,
                            key2 = BenchmarkState.tasks.size
                        ) { BenchmarkState.tasks[index].percent }
                        LinearProgressIndicator(
                            progress = percent / 100f,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                )
            }
        }
        LocalThemePrefs.current.showDialogIfNeed()
    }
}
