package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kotlity.mlkitdocumentscanner.R

@Composable
fun CustomSwitcher(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    title: String = stringResource(id = R.string.gallery_import_allowed_switcher_title),
    isChecked: Boolean,
    onToggleChecked: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Text(text = title)
        Switch(
            checked = isChecked,
            onCheckedChange = { updateIsChecked ->
                onToggleChecked(updateIsChecked)
            },
            thumbContent = {
                Icon(
                    imageVector = if (isChecked) Icons.Outlined.Check else Icons.Outlined.Close,
                    contentDescription = null
                )
            }
        )
    }
}