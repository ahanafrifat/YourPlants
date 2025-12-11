package com.ahanafrifat.yourplants.enhos.presentation.echos.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme

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
    modifier: Modifier = Modifier
){

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