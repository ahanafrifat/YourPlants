package com.ahanafrifat.yourplants.enhos.presentation.echos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme

@Composable
fun EchosRoot(
    viewModel: EchosViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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

}

@Preview
@Composable
private fun Preview(){
    YourPlantsTheme {
        EchosScreen(
            state = EchosState(),
            onAction = {}
        )
    }
}