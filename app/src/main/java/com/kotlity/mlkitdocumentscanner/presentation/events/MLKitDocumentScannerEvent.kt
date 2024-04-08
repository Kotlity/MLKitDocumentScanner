package com.kotlity.mlkitdocumentscanner.presentation.events

import android.content.Context
import android.net.Uri

sealed interface MLKitDocumentScannerEvent {

    data class OnGalleryImportAllowedAddValue(val galleryImportAllowed: Boolean):
        MLKitDocumentScannerEvent
    data class OnSliderPageLimitChange(val pageLimit: Int): MLKitDocumentScannerEvent
    data class OnPageLimitAddValue(val pageLimit: Int): MLKitDocumentScannerEvent
    data class OnResultFormatsAddValue(val resultFormats: Int): MLKitDocumentScannerEvent
    data class OnScannerModeAddValue(val scannerMode: Int): MLKitDocumentScannerEvent
    data class OnScannerImagesUpdate(val scannerImages: List<Uri>): MLKitDocumentScannerEvent
    data class OnScanDocument(
        val galleryImportAllowed: Boolean,
        val pageLimit: Int,
        val resultFormats: Int,
        val scannerMode: Int,
        val context: Context
    ): MLKitDocumentScannerEvent
    data class OnPDFFileUriUpdate(val pdfFileUri: Uri): MLKitDocumentScannerEvent
    data class OnPDFFileDownload(val name: String, val fileUri: Uri): MLKitDocumentScannerEvent

    data object OnAlertDialogVisibilityChange: MLKitDocumentScannerEvent
    data object OnScannerResultCleared: MLKitDocumentScannerEvent
}