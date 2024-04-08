package com.kotlity.mlkitdocumentscanner.presentation.viewmodels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlity.mlkitdocumentscanner.domain.use_cases.AppUseCases
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_GALLERY_IMPORT_ALLOWED
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_PAGE_LIMIT
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_RESULT_FORMATS
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DEFAULT_SCANNER_MODE
import com.kotlity.mlkitdocumentscanner.helpers.flows.toStateFlow
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import com.kotlity.mlkitdocumentscanner.helpers.response.Response
import com.kotlity.mlkitdocumentscanner.presentation.events.MLKitDocumentScannerEvent
import com.kotlity.mlkitdocumentscanner.presentation.events.PDFFileDownloadResult
import com.kotlity.mlkitdocumentscanner.presentation.states.MLKitDocumentScannerState
import com.kotlity.mlkitdocumentscanner.presentation.states.ScannerResult
import com.kotlity.mlkitdocumentscanner.presentation.text_convertor.UiText
import com.kotlity.mlkitdocumentscanner.presentation.text_convertor.asErrorUiText
import com.kotlity.mlkitdocumentscanner.presentation.text_convertor.asSuccessUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MLKitDocumentScannerViewModel @Inject constructor(private val appUseCases: AppUseCases): ViewModel() {

    val isGalleryImportAllowed = appUseCases.galleryImportAllowedGetValueUseCase().toStateFlow(scope = viewModelScope, initialValue = DEFAULT_GALLERY_IMPORT_ALLOWED)
    val pageLimit = appUseCases.pageLimitGetValueUseCase().toStateFlow(scope = viewModelScope, initialValue = DEFAULT_PAGE_LIMIT)
    val resultFormats = appUseCases.resultFormatsGetValueUseCase().toStateFlow(scope = viewModelScope, initialValue = DEFAULT_RESULT_FORMATS)
    val scannerMode = appUseCases.scannerModeGetValueUseCase().toStateFlow(scope = viewModelScope, initialValue = DEFAULT_SCANNER_MODE)

    private var pdfFileDownloadJob: Job? = null

    var mlKitDocumentScannerState by mutableStateOf(MLKitDocumentScannerState())
        private set

    private val pdfFileDownloadResultChannel = Channel<PDFFileDownloadResult>()
    val pdfFileDownloadResultFlow = pdfFileDownloadResultChannel.receiveAsFlow()

    fun onEvent(mlKitDocumentScannerEvent: MLKitDocumentScannerEvent) {
        when(mlKitDocumentScannerEvent) {
            is MLKitDocumentScannerEvent.OnGalleryImportAllowedAddValue -> onGalleryImportAllowedAddValue(mlKitDocumentScannerEvent.galleryImportAllowed)
            is MLKitDocumentScannerEvent.OnSliderPageLimitChange -> onSliderPageLimitChange(mlKitDocumentScannerEvent.pageLimit)
            is MLKitDocumentScannerEvent.OnPageLimitAddValue -> onPageLimitAddValue(mlKitDocumentScannerEvent.pageLimit)
            is MLKitDocumentScannerEvent.OnResultFormatsAddValue -> onResultFormatsAddValue(mlKitDocumentScannerEvent.resultFormats)
            is MLKitDocumentScannerEvent.OnScannerModeAddValue -> onScannerModeAddValue(mlKitDocumentScannerEvent.scannerMode)
            is MLKitDocumentScannerEvent.OnScannerImagesUpdate -> onScannerImagesUpdate(mlKitDocumentScannerEvent.scannerImages)
            is MLKitDocumentScannerEvent.OnScanDocument -> onScanDocument(
                mlKitDocumentScannerEvent.galleryImportAllowed,
                mlKitDocumentScannerEvent.pageLimit,
                mlKitDocumentScannerEvent.resultFormats,
                mlKitDocumentScannerEvent.scannerMode,
                mlKitDocumentScannerEvent.context
            )
            is MLKitDocumentScannerEvent.OnPDFFileUriUpdate -> onPDFFileUriUpdate(mlKitDocumentScannerEvent.pdfFileUri)
            is MLKitDocumentScannerEvent.OnPDFFileDownload -> onPDFFileDownload(mlKitDocumentScannerEvent.name, mlKitDocumentScannerEvent.fileUri)
            MLKitDocumentScannerEvent.OnAlertDialogVisibilityChange -> onAlertDialogVisibilityChange()
            MLKitDocumentScannerEvent.OnScannerResultCleared -> onScannerResultCleared()
        }
    }

    private fun onGalleryImportAllowedAddValue(galleryImportAllowed: Boolean) {
        viewModelScope.launch {
            appUseCases.galleryImportAllowedAddValueUseCase(galleryImportAllowed)
        }
    }

    private fun onSliderPageLimitChange(pageLimit: Int) {
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(currentPageNumberLimit = pageLimit)
    }

    private fun onPageLimitAddValue(pageLimit: Int) {
        viewModelScope.launch {
            appUseCases.pageLimitAddValueUseCase(pageLimit)
        }
    }

    private fun onResultFormatsAddValue(resultFormats: Int) {
        viewModelScope.launch {
            appUseCases.resultFormatsAddValueUseCase(resultFormats)
        }
    }

    private fun onScannerModeAddValue(scannerMode: Int) {
        viewModelScope.launch {
            appUseCases.scannerModeAddValueUseCase(scannerMode)
        }
    }

    private fun onScannerImagesUpdate(scannerImages: List<Uri>) {
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(scannerImages = scannerImages)
    }

    private fun onScanDocument(
        galleryImportAllowed: Boolean,
        pageLimit: Int,
        resultFormats: Int,
        scannerMode: Int,
        context: Context
    ) {
        viewModelScope.launch {
            updateDownloadPDFFileButtonState()
            appUseCases.mlKitScanDocumentUseCase(
                galleryImportAllowed,
                pageLimit,
                resultFormats,
                scannerMode,
                context,
                onSuccess = { intentSender ->
                    mlKitDocumentScannerState = mlKitDocumentScannerState.copy(scannerResult = ScannerResult(intentSender = intentSender))
                },
                onFailure = { scannerError ->
                    mlKitDocumentScannerState = mlKitDocumentScannerState.copy(scannerResult = ScannerResult(scannerError = scannerError))
                }
            )
        }
    }

    private fun onPDFFileUriUpdate(pdfFileUri: Uri) {
        val updatedPDFFileUri = mlKitDocumentScannerState.pdfFileDownloadState.copy(pdfFileUri = pdfFileUri)
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(pdfFileDownloadState = updatedPDFFileUri)
    }

    private fun onPDFFileDownload(name: String, fileUri: Uri) {
        pdfFileDownloadJob?.cancel()
        pdfFileDownloadJob = appUseCases.pdfFileDownloaderUseCase(name, fileUri).onEach { result ->
            result.handlePDFDownloadResult(name, fileUri)
        }.launchIn(viewModelScope)
    }

    private suspend fun Response<PDFFileDownloaderSuccess, PDFFileDownloaderError>.handlePDFDownloadResult(name: String, fileUri: Uri) {
        when (this) {
            is Response.Error -> {
                updateDownloadPDFFileButtonState(pdfFileUri = fileUri)
                val errorMessage = asErrorUiText()
                sendAMessageToTheChannel(message = errorMessage)
            }
            is Response.Loading -> updateDownloadPDFFileButtonState(isDownloading = true, pdfFileUri = fileUri)
            is Response.Success -> {
                updateDownloadPDFFileButtonState(isAlreadyDownloadPDFFile = true)
                val successMessage = asSuccessUiText(name)
                sendAMessageToTheChannel(message = successMessage)
            }
        }
    }

    private fun updateDownloadPDFFileButtonState(
        isDownloading: Boolean = false,
        isAlreadyDownloadPDFFile: Boolean = false,
        pdfFileUri: Uri = Uri.EMPTY
    ) {
        val updatedPDFFileDownloadState = mlKitDocumentScannerState.pdfFileDownloadState.copy(
            isDownloading = isDownloading,
            isAlreadyDownloadPDFFile = isAlreadyDownloadPDFFile,
            pdfFileUri = pdfFileUri
        )
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(pdfFileDownloadState = updatedPDFFileDownloadState)
    }

    private suspend fun sendAMessageToTheChannel(message: UiText) {
        pdfFileDownloadResultChannel.send(PDFFileDownloadResult.OnPDFFileDownloadResult(message = message))
    }

    private fun onAlertDialogVisibilityChange() {
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(isAlertDialogVisible = !mlKitDocumentScannerState.isAlertDialogVisible)
    }

    private fun onScannerResultCleared() {
        mlKitDocumentScannerState = mlKitDocumentScannerState.copy(scannerResult = ScannerResult())
    }
}