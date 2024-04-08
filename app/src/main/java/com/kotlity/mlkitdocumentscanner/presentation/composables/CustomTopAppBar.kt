package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.kotlity.mlkitdocumentscanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String = stringResource(id = R.string.top_app_bar_title),
    icon: ImageVector = Icons.Outlined.MoreVert,
    onActionsClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            OutlinedIconButton(onClick = onActionsClick) {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    )
}