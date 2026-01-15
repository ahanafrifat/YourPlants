package com.ahanafrifat.yourplants.enhos.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.enhos.presentation.components.MoodSelectorRow
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

@Composable
fun MoodCard(
    selectedMood: MoodUi?,
    onMoodClick: (MoodUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(14.dp)
    ) {
        Text(
            text = stringResource(R.string.my_mood),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.select_default_mood_to_apply_to_all_new_entries),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))
        MoodSelectorRow(
            selectedMood = selectedMood,
            onMoodClick = onMoodClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodCardPreview(){
    YourPlantsTheme {
        MoodCard(
            selectedMood = null,
            onMoodClick = {},
            modifier = Modifier
        )
    }
}
