package com.ahanafrifat.yourplants.enhos.presentation.echos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosAction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.chips.MultiChoiceChip
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.Selectable
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.SelectableDropDownOptionsMenu
import com.ahanafrifat.yourplants.core.presentation.util.UiText
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoFilterChip
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.MoodChipContent
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

@Composable
fun EchoFilterRow(
    moodChipContent: MoodChipContent,
    hasActiveMoodFilters: Boolean,
    selectedEchoFilterChip: EchoFilterChip?,
    moods: List<Selectable<MoodUi>>,
    topicChipTitle: UiText,
    hasActiveTopicFilter: Boolean,
    topic: List<Selectable<String>>,
    onAction: (EchosAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var dropDownOffset by remember {
        mutableStateOf(IntOffset.Zero)
    }
    val configuration = LocalConfiguration.current
    val dropDownMaxHeight = (configuration.screenHeightDp * 0.3f).dp

    FlowRow(
        modifier = modifier
            .padding(16.dp)
            .onGloballyPositioned {
                dropDownOffset = IntOffset(
                    x = 0,
                    y = it.size.height
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        MultiChoiceChip(
            displayText = moodChipContent.title.asString(),
            onClick = {
                onAction(EchosAction.OnMoodChipClick)
            },
            leadingContent = {
                if (moodChipContent.iconRes.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy((-4).dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        moodChipContent.iconRes.forEach { iconRes ->
                            Image(
                                imageVector = ImageVector.vectorResource(iconRes),
                                contentDescription = moodChipContent.title.asString(),
                                modifier = Modifier
                                    .height(16.dp)
                            )
                        }
                    }
                }
            },
            isClearVisible = hasActiveMoodFilters,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.MOODS,
            isHighLighted = hasActiveMoodFilters || selectedEchoFilterChip == EchoFilterChip.MOODS,
            onCLearButtonClicked = {
                onAction(EchosAction.OnRemoveFilters(EchoFilterChip.MOODS))
            },
            dropDownMenu = {
                SelectableDropDownOptionsMenu(
                    items = moods,
                    itemDisplayText = { moodUi ->
                        moodUi.title.asString(context)
                    },
                    onDismiss = {
                        onAction(EchosAction.OnDismissMoodDropDown)
                    },
                    key = { moodUi ->
                        moodUi.title.asString(context)
                    },
                    onItemClick = { moodUi ->
                        onAction(EchosAction.OnFilterByMoodClick(moodUi.item))
                    },
                    dropDownOffSet = dropDownOffset,
                    maxDropDownHeight = dropDownMaxHeight,
                    leadingIcon = { moodUi ->
                        Image(
                            imageVector = ImageVector.vectorResource(moodUi.iconSet.fill),
                            contentDescription = moodUi.title.asString()
                        )
                    }
                )
            }
        )

        MultiChoiceChip(
            displayText = topicChipTitle.asString(),
            onClick = {
                onAction(EchosAction.OnTopicChipClick)
            },
            leadingContent = {

            },
            isClearVisible = hasActiveTopicFilter,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.TOPICS,
            isHighLighted = hasActiveTopicFilter || selectedEchoFilterChip == EchoFilterChip.TOPICS,
            onCLearButtonClicked = {
                onAction(EchosAction.OnRemoveFilters(EchoFilterChip.TOPICS))
            },
            dropDownMenu = {
                if (topic.isEmpty()) {
                    SelectableDropDownOptionsMenu(
                        items = listOf(
                            Selectable(
                                item = stringResource(R.string.you_don_t_have_any_topics_yet),
                                selected = false
                            )
                        ),
                        itemDisplayText = { it },
                        onDismiss = {
                            onAction(EchosAction.OnDismissTopicDropDown)
                        },
                        key = { it },
                        onItemClick = {},
                        dropDownOffSet = dropDownOffset,
                        maxDropDownHeight = dropDownMaxHeight
                    )
                } else {
                    SelectableDropDownOptionsMenu(
                        items = topic,
                        itemDisplayText = { topic ->
                            topic
                        },
                        onDismiss = {
                            onAction(EchosAction.OnDismissTopicDropDown)
                        },
                        key = { topic ->
                            topic
                        },
                        onItemClick = { topic ->
                            onAction(EchosAction.OnFilterByTopicClick(topic.item))
                        },
                        dropDownOffSet = dropDownOffset,
                        maxDropDownHeight = dropDownMaxHeight,
                        leadingIcon = { topic ->
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                                contentDescription = topic
                            )
                        }
                    )
                }
            }
        )
    }
}