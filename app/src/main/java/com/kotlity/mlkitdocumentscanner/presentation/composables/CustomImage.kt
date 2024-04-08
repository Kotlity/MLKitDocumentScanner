package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    model: Any,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    AsyncImage(
        modifier = modifier,
        model = model,
        contentScale = contentScale,
        contentDescription = null
    )
}