package com.kotlity.mlkitdocumentscanner.presentation.screens

import android.app.Activity.RESULT_OK
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.flowWithLifecycle
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.kotlity.mlkitdocumentscanner.presentation.states.DocumentScannerPreferencesState
import com.kotlity.mlkitdocumentscanner.presentation.states.MLKitDocumentScannerState
import com.kotlity.mlkitdocumentscanner.presentation.time_formatter.formatTime
import com.kotlity.mlkitdocumentscanner.R
import com.kotlity.mlkitdocumentscanner.helpers.document_scanner_types.ResultScannerFormats
import com.kotlity.mlkitdocumentscanner.helpers.document_scanner_types.ScannerModes
import com.kotlity.mlkitdocumentscanner.presentation.composables.CustomAlertDialog
import com.kotlity.mlkitdocumentscanner.presentation.composables.CustomRadioButton
import com.kotlity.mlkitdocumentscanner.presentation.composables.CustomSlider
import com.kotlity.mlkitdocumentscanner.presentation.composables.CustomSwitcher
import com.kotlity.mlkitdocumentscanner.presentation.composables.CustomTopAppBar
import com.kotlity.mlkitdocumentscanner.presentation.composables.MLKitDocumentScannerGeneralContent
import com.kotlity.mlkitdocumentscanner.presentation.events.MLKitDocumentScannerEvent
import com.kotlity.mlkitdocumentscanner.presentation.events.PDFFileDownloadResult
import kotlinx.coroutines.flow.Flow
import kotlin.math.roundToInt

@Composable
fun MLKitDocumentScannerScreen(
    mlKitDocumentScannerState: MLKitDocumentScannerState,
    documentScannerPreferencesState: DocumentScannerPreferencesState,
    pdfFileDownloadResultFlow: Flow<PDFFileDownloadResult>,
    onEvent: (MLKitDocumentScannerEvent) -> Unit
) {

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val scannerResult = mlKitDocumentScannerState.scannerResult
    val scannedImages = mlKitDocumentScannerState.scannerImages
    val isGalleryImportAllowed = documentScannerPreferencesState.isGalleryImportAllowed
    val resultFormats = documentScannerPreferencesState.resultFormats
    val scannerMode = documentScannerPreferencesState.scannerMode
    val pageNumberLimit = documentScannerPreferencesState.pageNumberLimit
    val sliderPageNumberLimit = mlKitDocumentScannerState.currentPageNumberLimit
    val isAlertDialogVisible = mlKitDocumentScannerState.isAlertDialogVisible
    val isAlreadyDownloadPDFFile = mlKitDocumentScannerState.pdfFileDownloadState.isAlreadyDownloadPDFFile
    val isDownloadingPDFFile = mlKitDocumentScannerState.pdfFileDownloadState.isDownloading
    val pdfFileUri = mlKitDocumentScannerState.pdfFileDownloadState.pdfFileUri

    val isAllFormatsSupported = resultFormats == ResultScannerFormats.JPEG_PDF.value
    val isJPEGFormatSupported = resultFormats == ResultScannerFormats.JPEG.value
    val isPDFFormatSupported = resultFormats == ResultScannerFormats.PDF.value

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { activityResult ->
            onEvent(MLKitDocumentScannerEvent.OnScannerResultCleared)
            if (activityResult.resultCode == RESULT_OK) {
                val result = GmsDocumentScanningResult.fromActivityResultIntent(activityResult.data)
                result?.let { scanResult ->
                    if (isJPEGFormatSupported || isAllFormatsSupported) {
                        val uris: List<Uri> = scanResult.pages?.map { it.imageUri } ?: emptyList()
                        onEvent(MLKitDocumentScannerEvent.OnScannerImagesUpdate(uris))
                    }
                    if (isPDFFormatSupported || isAllFormatsSupported) {
                        scanResult.pdf?.uri?.let { uri ->
                            onEvent(MLKitDocumentScannerEvent.OnPDFFileUriUpdate(uri))
                        }
                    }
                }
            }
        }
    )

    LaunchedEffect(key1 = pageNumberLimit) {
        if (sliderPageNumberLimit != pageNumberLimit) {
            onEvent(MLKitDocumentScannerEvent.OnSliderPageLimitChange(pageNumberLimit))
        }
    }

    LaunchedEffect(key1 = scannerResult) {
        scannerResult.intentSender?.let {
            val intentSenderRequest = IntentSenderRequest.Builder(it).build()
            scannerLauncher.launch(intentSenderRequest)
        }
    }

    LaunchedEffect(key1 = Unit) {
        pdfFileDownloadResultFlow.flowWithLifecycle(lifecycle).collect { result ->
            when(result) {
                is PDFFileDownloadResult.OnPDFFileDownloadResult -> {
                    val downloadResultMessage = result.message.asString(context)
                    Toast.makeText(context, downloadResultMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                onActionsClick = {
                    onEvent(MLKitDocumentScannerEvent.OnAlertDialogVisibilityChange)
                }
            )
        },
        containerColor = if (isDownloadingPDFFile) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.background
    ) { paddingValues ->
        MLKitDocumentScannerGeneralContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            images = scannedImages,
            scannerError = scannerResult.scannerError,
            isDownloadingPDFFile = isDownloadingPDFFile,
            isPDFFileDownloadButtonVisible = (isPDFFormatSupported || isAllFormatsSupported) && scannedImages.isNotEmpty(),
            isPDFFileDownloadButtonEnabled = !isAlreadyDownloadPDFFile && !isDownloadingPDFFile,
            onScanButtonClick = {
                onEvent(
                    MLKitDocumentScannerEvent.OnScanDocument(
                        galleryImportAllowed = isGalleryImportAllowed,
                        pageLimit = pageNumberLimit,
                        resultFormats = resultFormats,
                        scannerMode = scannerMode,
                        context = context
                    )
                )
            },
            onPDFFileDownloadClick = {
                if (pdfFileUri != Uri.EMPTY) {
                    val pdfFileName = System.currentTimeMillis().formatTime() + ".pdf"
                    onEvent(MLKitDocumentScannerEvent.OnPDFFileDownload(pdfFileName, pdfFileUri))
                }
            }
        )
    }

    if (isAlertDialogVisible) {
        CustomAlertDialog(
            onDismiss = {
                onEvent(MLKitDocumentScannerEvent.OnAlertDialogVisibilityChange)
            },
            content = {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = R.dimen._5dp))
                    ) {
                        OutlinedIconButton(
                            modifier = Modifier.align(Alignment.End),
                            onClick = {
                                onEvent(MLKitDocumentScannerEvent.OnAlertDialogVisibilityChange)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                        CustomSwitcher(
                            modifier = Modifier.fillMaxWidth(),
                            isChecked = isGalleryImportAllowed,
                            onToggleChecked = {
                                onEvent(MLKitDocumentScannerEvent.OnGalleryImportAllowedAddValue(it))
                            }
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._15dp)))
                        CustomSlider(
                            modifier = Modifier.fillMaxWidth(),
                            sliderModifier = Modifier.weight(1f),
                            number = sliderPageNumberLimit.toFloat(),
                            onNumberChange = {
                                val roundedNumber = it.roundToInt()
                                onEvent(MLKitDocumentScannerEvent.OnSliderPageLimitChange(roundedNumber))
                            },
                            onNumberChangeFinished = {
                                if (sliderPageNumberLimit != pageNumberLimit) onEvent(MLKitDocumentScannerEvent.OnPageLimitAddValue(sliderPageNumberLimit))
                            }
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._15dp)))
                        Text(text = stringResource(id = R.string.scanner_format_title))
                        ResultScannerFormats.entries.forEach { scannerFormat ->
                            CustomRadioButton(
                                isSelected = scannerFormat.value == resultFormats,
                                title = when (scannerFormat) {
                                    ResultScannerFormats.JPEG -> stringResource(id = R.string.scanner_format_jpeg)
                                    ResultScannerFormats.PDF -> stringResource(id = R.string.scanner_format_pdf)
                                    ResultScannerFormats.JPEG_PDF -> stringResource(id = R.string.scanner_format_jpeg_pdf)
                                },
                                onRadioButtonClick = {
                                    onEvent(MLKitDocumentScannerEvent.OnResultFormatsAddValue(scannerFormat.value))
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._15dp)))
                        Text(text = stringResource(id = R.string.scanner_format_title))
                        ScannerModes.entries.forEach { scannerEnumMode ->
                            CustomRadioButton(
                                isSelected = scannerEnumMode.value == scannerMode,
                                title = when (scannerEnumMode) {
                                    ScannerModes.FULL -> stringResource(id = R.string.scanner_mode_full)
                                    ScannerModes.BASE_WITH_FILTER -> stringResource(id = R.string.scanner_mode_base_with_filter)
                                    ScannerModes.BASE -> stringResource(id = R.string.scanner_mode_base)
                                },
                                onRadioButtonClick = {
                                    onEvent(MLKitDocumentScannerEvent.OnScannerModeAddValue(scannerEnumMode.value))
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}