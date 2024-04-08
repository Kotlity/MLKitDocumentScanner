package com.kotlity.mlkitdocumentscanner.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.kotlity.mlkitdocumentscanner.R

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    sliderModifier: Modifier = Modifier,
    spacer: Dp = dimensionResource(id = R.dimen._5dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(spacer),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    number: Float,
    title: String = stringResource(id = R.string.scanned_images_limit_title),
    numberRange: ClosedFloatingPointRange<Float> = 1f..10f,
    steps: Int = 8,
    onNumberChange: (Float) -> Unit,
    onNumberChangeFinished: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Text(text = title + " ${number.toInt()}")
        Slider(
            modifier = sliderModifier,
            value = number,
            onValueChange = onNumberChange,
            valueRange = numberRange,
            steps = steps,
            onValueChangeFinished = onNumberChangeFinished
        )
    }
}