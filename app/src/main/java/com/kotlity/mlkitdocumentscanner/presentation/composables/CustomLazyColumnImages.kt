package com.kotlity.mlkitdocumentscanner.presentation.composables

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.kotlity.mlkitdocumentscanner.R

@Composable
fun CustomLazyColumnImages(
    modifier: Modifier = Modifier,
    images: List<Uri>,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(dimensionResource(id = R.dimen._5dp)),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(dimensionResource(id = R.dimen._5dp)),
    isPDFFileDownloadButtonVisible: Boolean,
    isPDFFileDownloadButtonEnabled: Boolean,
    onScanButtonClick: () -> Unit,
    onPDFFileDownloadClick: () -> Unit
) {

    val deviceWidth = Modifier.fillMaxWidth()

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        item {
            if (isPDFFileDownloadButtonVisible) {
                Row(
                    modifier = deviceWidth,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomButton(onClick = onScanButtonClick)
                    CustomButton(
                        isEnabled = isPDFFileDownloadButtonEnabled,
                        text = stringResource(id = R.string.download_pdf_file_button_text),
                        onClick = onPDFFileDownloadClick
                    )
                }
            } else {
                CustomBox(
                    modifier = deviceWidth,
                    content = {
                        CustomButton(onClick = onScanButtonClick)
                    }
                )
            }
        }
        items(images) { imageUri ->
            CustomImage(
                modifier = deviceWidth,
                model = imageUri
            )
        }
    }
}