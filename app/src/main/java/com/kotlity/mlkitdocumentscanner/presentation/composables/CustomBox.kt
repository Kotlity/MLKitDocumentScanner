package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable() (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        content = content
    )
}