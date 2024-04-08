package com.kotlity.mlkitdocumentscanner.presentation.events

import com.kotlity.mlkitdocumentscanner.presentation.text_convertor.UiText

sealed class PDFFileDownloadResult(val message: UiText) {

    class OnPDFFileDownloadResult(message: UiText): PDFFileDownloadResult(message = message)
}