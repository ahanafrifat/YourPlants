package com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme

@Composable
fun <T> SelectableDropDownOptionsMenu(
    items: List<Selectable<T>>,
    itemDisplayText: (T) -> String,
    onDismiss: () -> Unit,
    key: (T) -> Any,
    onItemClick: (Selectable<T>) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    dropDownOffSet: IntOffset = IntOffset.Zero,
    maxDropDownHeight: Dp = Dp.Unspecified,
    dropdownExtras: SelectableOptionExtras? = null
) {
    Popup(
        onDismissRequest = onDismiss,
        offset = dropDownOffSet
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 4.dp,
            modifier = modifier
                .heightIn(max = maxDropDownHeight)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .animateContentSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) { ->
                items(
                    items = items,
                    key = { key(it.item) }
                ) { selectable ->
                    Row(
                        modifier = Modifier
                            .animateItem()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (selectable.selected) {
                                    MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.05f)
                                } else MaterialTheme.colorScheme.surface
                            )
                            .clickable {
                                onItemClick(selectable)
                            }
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        leadingIcon?.invoke()
                        Text(
                            text = itemDisplayText(selectable.item),
                            modifier = Modifier.weight(1f)
                        )

                        if (selectable.selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                if (dropdownExtras != null && dropdownExtras.text.isNotEmpty()) {
                    item(key = true) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .clickable {
                                    dropdownExtras.onClick()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                            Text(
                                text = stringResource(R.string.create_entry, dropdownExtras.text),
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun SelectableDropDownOptionsMenuPreview() {
    YourPlantsTheme {
        SelectableDropDownOptionsMenu(
            items = listOf(
                Selectable(
                    item = "Topic 1",
                    selected = true
                ),
                Selectable(
                    item = "Topic 2",
                    selected = true
                ),
                Selectable(
                    item = "Topic 3",
                    selected = false
                )
            ),
            key = { it },
            itemDisplayText = { it },
            onDismiss = {},
            onItemClick = {},
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                    contentDescription = null
                )
            },
            maxDropDownHeight = 500.dp,
            dropdownExtras = SelectableOptionExtras(
                text = "Add Topic",
                onClick = {}
            )
        )
    }
}