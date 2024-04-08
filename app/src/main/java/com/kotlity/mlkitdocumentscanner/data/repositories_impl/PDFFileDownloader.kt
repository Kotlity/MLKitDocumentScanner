package com.kotlity.mlkitdocumentscanner.data.repositories_impl

import android.content.Context
import android.net.Uri
import com.kotlity.mlkitdocumentscanner.domain.repositories.FileDownloader
import com.kotlity.mlkitdocumentscanner.helpers.dispatchers.AppDispatchers
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderError
import com.kotlity.mlkitdocumentscanner.helpers.response.PDFFileDownloaderSuccess
import com.kotlity.mlkitdocumentscanner.helpers.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class PDFFileDownloader @Inject constructor(
    private val context: Context,
    private val appDispatchers: AppDispatchers
): FileDownloader<PDFFileDownloaderSuccess, PDFFileDownloaderError> {

    override fun downloadFile(name: String, fileUri: Uri): Flow<Response<PDFFileDownloaderSuccess, PDFFileDownloaderError>> {
        return flow<Response<PDFFileDownloaderSuccess, PDFFileDownloaderError>> {
            try {
                emit(Response.Loading())
                val contentResolver = context.contentResolver
                val fileToDownload = File(context.filesDir, name)
                val fileOutputStream = FileOutputStream(fileToDownload)
                contentResolver.openInputStream(fileUri).use { inputStream ->
                    inputStream?.copyTo(fileOutputStream)
                }
                emit(Response.Success(PDFFileDownloaderSuccess.DOWNLOADSUCCESS))
            }
            catch (e: IOException) {
                emit(Response.Error(PDFFileDownloaderError.IOEXCEPTION))
            }
            catch (e: FileSystemException) {
                emit(Response.Error(PDFFileDownloaderError.FILESYSTEMEXCEPTION))
            }
            catch (e: OutOfMemoryError) {
                emit(Response.Error(PDFFileDownloaderError.OUTOFMEMORYERROR))
            }
            catch (e: NumberFormatException) {
                emit(Response.Error(PDFFileDownloaderError.NUMBERFORMATEXCEPTION))
            }
            catch(e: SecurityException) {
                emit(Response.Error(PDFFileDownloaderError.SECURITYEXCEPTION))
            }
        }
            .flowOn(appDispatchers.io)
    }
}