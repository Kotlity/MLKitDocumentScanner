package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.kotlity.mlkitdocumentscanner.R
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants._18sp

@Composable
fun ErrorSection(
    modifier: Modifier = Modifier,
    verticalSpace: Dp = dimensionResource(id = R.dimen._5dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(verticalSpace, Alignment.CenterVertically),
    title: String,
    buttonText: String = stringResource(id = R.string.scanner_button_error_text),
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = _18sp,
                fontWeight = FontWeight.Bold
            )
        )
        CustomButton(
            text = buttonText,
            onClick = onRetryClick
        )
    }
}