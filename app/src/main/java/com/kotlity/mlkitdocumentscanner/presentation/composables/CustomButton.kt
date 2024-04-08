package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.kotlity.mlkitdocumentscanner.R
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants._18sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String = stringResource(id = R.string.scanner_button_text),
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = _18sp
            )
        )
    }
}