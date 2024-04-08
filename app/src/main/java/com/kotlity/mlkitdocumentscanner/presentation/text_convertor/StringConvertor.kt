package com.kotlity.mlkitdocumentscanner.presentation.text_convertor

import com.kotlity.mlkitdocumentscanner.R
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import com.kotlity.mlkitdocumentscanner.helpers.response.Response

fun PDFFileDownloaderSuccess.asUiText(fileName: String): UiText {
    return when(this) {
        PDFFileDownloaderSuccess.DOWNLOADSUCCESS -> UiText.StringResource(
            id = R.string.pdf_file_has_been_successfully_downloaded,
            args = arrayOf(fileName)
        )
    }
}

fun PDFFileDownloaderError.asUiText(): UiText {
    return when(this) {
        PDFFileDownloaderError.IOEXCEPTION -> UiText.StringResource(
            id = R.string.pdf_file_io_exception
        )
        PDFFileDownloaderError.FILESYSTEMEXCEPTION -> UiText.StringResource(
            id = R.string.pdf_file_file_system_exception
        )
        PDFFileDownloaderError.OUTOFMEMORYERROR -> UiText.StringResource(
            id = R.string.pdf_file_out_of_memory_error
        )
        PDFFileDownloaderError.NUMBERFORMATEXCEPTION -> UiText.StringResource(
            id = R.string.pdf_file_number_format_exception
        )
        PDFFileDownloaderError.SECURITYEXCEPTION -> UiText.StringResource(
            id = R.string.pdf_file_security_exception
        )
    }
}

fun Response.Success<PDFFileDownloaderSuccess, *>.asSuccessUiText(fileName: String): UiText {
    return data.asUiText(fileName)
}

fun Response.Error<*, PDFFileDownloaderError>.asErrorUiText(): UiText {
    return errorMessage.asUiText()
}