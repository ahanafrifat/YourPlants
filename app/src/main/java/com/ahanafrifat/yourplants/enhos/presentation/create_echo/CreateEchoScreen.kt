package com.ahanafrifat.yourplants.enhos.presentation.create_echo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateEchoRoot(
    viewModel: CreateEchoViewModel = koinViewModel()
){
    val state =viewModel.state.collectAsStateWithLifecycle()
}

@Composable
fun CreateEchoScreen(
    modifier: Modifier
) {

}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CreateEchoScreenPreview() {
    YourPlantsTheme {

    }
}