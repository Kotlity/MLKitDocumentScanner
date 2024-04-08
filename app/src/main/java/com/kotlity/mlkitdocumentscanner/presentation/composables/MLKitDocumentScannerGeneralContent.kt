package com.kotlity.mlkitdocumentscanner.presentation.composables

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError

@Composable
fun MLKitDocumentScannerGeneralContent(
    modifier: Modifier = Modifier,
    images: List<Uri>,
    scannerError: ScannerError?,
    isDownloadingPDFFile: Boolean,
    isPDFFileDownloadButtonVisible: Boolean,
    isPDFFileDownloadButtonEnabled: Boolean,
    onScanButtonClick: () -> Unit,
    onPDFFileDownloadClick: () -> Unit
) {

    val deviceSize = Modifier.fillMaxSize()

    if (images.isNotEmpty()) {
        Column(modifier = modifier) {
            CustomLazyColumnImages(
                modifier = modifier,
                images = images,
                isPDFFileDownloadButtonVisible = isPDFFileDownloadButtonVisible,
                isPDFFileDownloadButtonEnabled = isPDFFileDownloadButtonEnabled,
                onScanButtonClick = onScanButtonClick,
                onPDFFileDownloadClick = onPDFFileDownloadClick
            )
        }
    } else {
        CustomBox(
            modifier = deviceSize,
            content = {
                CustomButton(onClick = onScanButtonClick)
            }
        )
    }
    if (isDownloadingPDFFile) {
        CustomBox(
            modifier = deviceSize,
            content = {
                CircularProgressIndicator()
            }
        )
    }
    scannerError?.let { error ->
        CustomBox(
            modifier = deviceSize,
            content = {
                ErrorSection(
                    modifier = Modifier.wrapContentWidth(),
                    title = error.message,
                    onRetryClick = onScanButtonClick
                )
            }
        )
    }
}