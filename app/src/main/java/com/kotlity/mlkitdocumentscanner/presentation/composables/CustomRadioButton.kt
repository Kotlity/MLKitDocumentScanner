package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CustomRadioButton(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    isSelected: Boolean,
    title: String,
    onRadioButtonClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(onClick = onRadioButtonClick),
        verticalAlignment = verticalAlignment,
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onRadioButtonClick
        )
        Text(text = title)
    }
}