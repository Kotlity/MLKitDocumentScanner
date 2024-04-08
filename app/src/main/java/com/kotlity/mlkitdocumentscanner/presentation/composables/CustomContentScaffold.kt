package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomContentScaffold(
    modifier: Modifier = Modifier,
    topBarContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBarContent,
        content = content
    )
}