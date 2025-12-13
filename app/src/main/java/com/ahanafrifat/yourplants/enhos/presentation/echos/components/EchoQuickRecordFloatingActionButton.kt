package com.ahanafrifat.yourplants.enhos.presentation.echos.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.BubbleFloatingActionButtonColors
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.rememberBubbleFloatingActionButtonColors
import kotlin.math.roundToInt

@Composable
fun EchoQuickRecordFloatingActionButton(
    isQuickRecording: Boolean,
    onClick: () -> Unit,
    onLongPressStart: () -> Unit,
    onLongPressEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hapticFeedback = LocalHapticFeedback.current
    val cancelButtonOffset = (-100).dp
    val cancelButtonOffsetPx = with(LocalDensity.current) {
        cancelButtonOffset.toPx()
    }

    var dragOffSetX by remember {
        mutableFloatStateOf(0f)
    }
    var needToHandelLongClickEnd by remember {
        mutableStateOf(false)
    }
    val isWithinCancelThreshold by remember(cancelButtonOffsetPx) {
        derivedStateOf {
            dragOffSetX <= cancelButtonOffsetPx * 0.8f
        }
    }

    LaunchedEffect(isWithinCancelThreshold) {
        if (isWithinCancelThreshold) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    val fabPositionOffset by remember {
        derivedStateOf {
            IntOffset(
                x = dragOffSetX.toInt().coerceIn(
                    minimumValue = cancelButtonOffsetPx.roundToInt(),
                    maximumValue = 0
                ),
                y = 0
            )
        }
    }

//    Box(
//        modifier = modifier
//            .pointerInput(Unit){
//                detectDragGesturesAfterLongPress(
//                    onDragStart = {
//                        needToHandelLongClickEnd = true
//                        hapticFeedback.performHapticFeedback((HapticFeedbackType.LongPress))
//                        onLongPressStart
//                    },
//                    onDragEnd = {
//                        if (needToHandelLongClickEnd){
//                            needToHandelLongClickEnd = false
//                            onLongPressEnd(isWithinCancelThreshold)
//                            dragOffSetX =0f
//                        }
//                    },
//                    onDragCancel = {},
//                    onDrag = {}
//                )
//            }
//    )

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

@Preview
@Composable
private fun EchoQuickRecordFloatingActionButtonPreview() {
    YourPlantsTheme {
//        EchoQuickRecordFloatingActionButton(
//            onClick = {},
//            modifier = Modifier
//        )
    }
}