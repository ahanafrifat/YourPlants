package com.ahanafrifat.yourplants.enhos.presentation.echos.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.Microphone
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.buttonGradient
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.buttonGradientPressed
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.primary90
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.primary95

@Composable
fun EchoRecordFloatingActionButton(
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
){
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_new_entry)
        )
    }
}

@Composable
fun EchoBubbleFloatingActionButton(
    showBubble: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    primaryButtonSize: Dp = 56.dp
){
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .background(
                color = if (showBubble) {
                    MaterialTheme.colorScheme.primary95
                } else {
                    Color.Transparent
                },
                shape = CircleShape
            )
            .padding(10.dp)
            .background(
                color = if (showBubble) {
                    MaterialTheme.colorScheme.primary90
                } else {
                    Color.Transparent
                },
                shape = CircleShape
            )
            .padding(16.dp)
            .background(
                brush = if (isPressed) {
                    MaterialTheme.colorScheme.buttonGradientPressed
                }else{
                    MaterialTheme.colorScheme.buttonGradient
                },
                shape = CircleShape
            )
            .size(primaryButtonSize)
            .clip(CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

@Preview
@Composable
private fun EchoRecordFloatingActionButtonPreview(){
    YourPlantsTheme{
        EchoRecordFloatingActionButton(
            onClick = {},
            modifier = Modifier
        )
    }
}