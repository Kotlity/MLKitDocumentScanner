package com.kotlity.mlkitdocumentscanner.domain.use_cases.file_downloader_use_case

import android.net.Uri
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import com.kotlity.mlkitdocumentscanner.domain.repositories.FileDownloader
import javax.inject.Inject

class PDFFileDownloaderUseCase @Inject constructor(private val fileDownloader: FileDownloader<PDFFileDownloaderSuccess, PDFFileDownloaderError>) {

    operator fun invoke(name: String, fileUri: Uri) = fileDownloader.downloadFile(name, fileUri)
}