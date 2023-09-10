@file:OptIn(ExperimentalMaterial3Api::class)

package com.softartdev.shared.material3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.softartdev.shared.AppState
import com.softartdev.shared.BenchmarkState
import com.softartdev.shared.DispatcherType
import com.softartdev.theme.pref.LocalThemePrefs
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class, ExperimentalLayoutApi::class)
@HiddenFromObjC
@Composable
fun BenchmarkBody(
    onBackClick: () -> Unit = { AppState.screenState.value = AppState.Screen.Settings },
    showLoading: State<Boolean> = BenchmarkState.showLoading
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
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    onClick = { BenchmarkState.runTasks() },
                    label = { Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null) },
                    enabled = !showLoading.value
                )
                SuggestionChip(onClick = BenchmarkState::release, { Icon(Icons.Default.Delete, null) })
            }
            BenchmarkState.tasks.forEach { task ->
                val percent: Int by remember(key1 = task.id) { derivedStateOf { task.percent.value } }
                LinearProgressIndicator(
                    progress = percent / 100f,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            val workingThreadsCount: Int by remember {
                derivedStateOf { BenchmarkState.tasks.size - BenchmarkState.tasks.count { it.percent.value == 0 } }
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.Center
            ) {
                DispatcherTypeMenu()
                SuggestionChip({}, { Text("$workingThreadsCount / ${BenchmarkState.n.value}") })
            }
            LinearProgressIndicator(
                progress = workingThreadsCount / 100f,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        LocalThemePrefs.current.showDialogIfNeed()
    }
}

@Composable
fun DispatcherTypeMenu() {
    var expanded by remember { mutableStateOf(false) }
    SuggestionChip(onClick = { expanded = true }, label = {
        Text(
            text = "${BenchmarkState.dispatcherType.value.name} ${if (expanded) "▲" else "▼"}",
            textAlign = TextAlign.Center
        )
    })
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DispatcherType.entries.forEach {
            DropdownMenuItem(onClick = {
                BenchmarkState.dispatcherType.value = it
                expanded = false
            }, text = { Text(it.name) })
        }
    }
}
