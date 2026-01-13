package com.ahanafrifat.yourplants.enhos.presentation.settings

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahanafrifat.yourplants.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoot(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreen(
        state = state,
        onAction = viewModel::onAction
    )

}

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        text = stringResource(R.string.settings),
//                        style = MaterialTheme.typography.
//                    )
//                }
//            )
//        }
//    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {

}