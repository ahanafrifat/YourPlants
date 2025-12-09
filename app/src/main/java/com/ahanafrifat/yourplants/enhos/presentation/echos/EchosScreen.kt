package com.ahanafrifat.yourplants.enhos.presentation.echos

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.bgGradient
import com.ahanafrifat.yourplants.core.presentation.util.ObserveAsEvents
import com.ahanafrifat.yourplants.enhos.presentation.echos.components.EchoFilterRow
import com.ahanafrifat.yourplants.enhos.presentation.echos.components.EchoList
import com.ahanafrifat.yourplants.enhos.presentation.echos.components.EchoRecordFloatingActionButton
import com.ahanafrifat.yourplants.enhos.presentation.echos.components.EchosEmptyBackground
import com.ahanafrifat.yourplants.enhos.presentation.echos.components.EchosTopBar
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.AudioCaptureMethod

@Composable
fun EchosRoot(
    viewModel: EchosViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted && state.currentCaptureMethod == AudioCaptureMethod.STANDARD){
            viewModel.onAction(EchosAction.OnAudioPermissionGranted)
        }
    }
    ObserveAsEvents(viewModel.events) { events ->
        when (events) {
            is EchosEvent.RequestAudioPermission -> {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    EchosScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EchosScreen(
    state: EchosState,
    onAction: (EchosAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            EchoRecordFloatingActionButton(
                onClick = {
                    onAction(EchosAction.OnFabClick)
                }
            )
        },
        topBar = {
            EchosTopBar(
                onSettingsClick = {
                    onAction(EchosAction.OnSettingClick)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = MaterialTheme.colorScheme.bgGradient
                )
                .padding(innerPadding)
        ) {
            EchoFilterRow(
                moodChipContent = state.moodChipContent,
                hasActiveMoodFilters = state.hasActiveMoodFilters,
                selectedEchoFilterChip = state.selectedEchoFilterChip,
                moods = state.moods,
                topicChipTitle = state.topicChipTitle,
                hasActiveTopicFilters = state.hasActiveTopicFilters,
                topics = state.topics,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxWidth()
            )
            when {
                state.isLoadingData -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .wrapContentSize(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                !state.hasEchosRecorded -> {
                    EchosEmptyBackground(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }

                else -> {
                    EchoList(
                        sections = state.echoDaySection,
                        onPlayClick = {
                            onAction(EchosAction.OnPlayEchoClick(it))
                        },
                        onPauseClick = {
                            onAction(EchosAction.OnPauseClick)
                        },
                        onTrackSizeAvailable = { trackSize ->
                            onAction(EchosAction.OnTrackSizeAvailable(trackSize))
                        }
                    )
                }
            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
private fun EchosScreenPreview() {
    YourPlantsTheme {
        EchosScreen(
            state = EchosState(
                isLoadingData = false,
                hasEchosRecorded = false
            ),
            onAction = {}
        )
    }
}