package com.ahanafrifat.yourplants.enhos.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

@Composable
fun MoodSelectorRow(
    selectedMood: MoodUi?,
    onMoodClick: (MoodUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MoodUi.entries.forEach { mood ->
            MoodItem(
                selected = mood == selectedMood,
                mood = mood,
                onClick = { onMoodClick(mood) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodSelectorRowPreview(){
    YourPlantsTheme {
        MoodSelectorRow(
            selectedMood = null,
            onMoodClick = {},
            modifier = Modifier
        )
    }
}