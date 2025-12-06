package com.ahanafrifat.yourplants.enhos.presentation.components

import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import com.ahanafrifat.yourplants.enhos.presentation.models.PlaybackState

@Composable
fun EchoPlaybackButton(
    playbackState: PlaybackState,
    onPlayClick: ()-> Unit,
    onPauseClick: ()-> Unit,
    colors: IconButtonColors,
    modifier: Modifier = Modifier
){
    FilledIconButton(
        onClick = when (playbackState){
            PlaybackState.PLAYING -> onPauseClick
            PlaybackState.PAUSED,
            PlaybackState.STOPPED -> onPlayClick
        }
    ) { }
}

@Preview
@Composable
private fun EchoPlaybackButtonPreview(){
    YourPlantsTheme {
        EchoPlaybackButton(
            playbackState = PlaybackState.PLAYING,
            onPlayClick = {},
            onPauseClick = {},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MoodUi.SAD.colorSet.desaturated,
                contentColor = MoodUi.SAD.colorSet.vivid
            )
        )
    }
}