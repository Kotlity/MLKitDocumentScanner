package com.kotlity.mlkitdocumentscanner.data.repositories_impl

import android.content.Context
import android.content.ContextWrapper
import android.content.IntentSender
import androidx.activity.ComponentActivity
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.kotlity.mlkitdocumentscanner.domain.repositories.DocumentScanner
import com.kotlity.mlkitdocumentscanner.helpers.constants.Constants.DOCUMENT_SCANNER_CANCELED_MESSAGE
import com.kotlity.mlkitdocumentscanner.helpers.dispatchers.AppDispatchers
import com.kotlity.mlkitdocumentscanner.helpers.document_scanner_types.ResultScannerFormats
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MLKitDocumentScanner @Inject constructor(
    private val appDispatchers: AppDispatchers
): DocumentScanner<IntentSender, ScannerError> {

    override suspend fun scanDocument(
        galleryImportAllowed: Boolean,
        pageLimit: Int,
        resultFormats: Int,
        scannerMode: Int,
        context: Context,
        onSuccess: (IntentSender) -> Unit,
        onFailure: (ScannerError) -> Unit
    ) {
        withContext(appDispatchers.io) {
            val scannerOptionsBuilder = GmsDocumentScannerOptions.Builder()
                .setGalleryImportAllowed(galleryImportAllowed)
                .setPageLimit(pageLimit)
                .setScannerMode(scannerMode)

            val scannerOptions = if (resultFormats.toString().length == 6) {
                scannerOptionsBuilder.setResultFormats(ResultScannerFormats.JPEG.value, ResultScannerFormats.PDF.value).build()
            } else scannerOptionsBuilder.setResultFormats(resultFormats).build()

            val scanner = GmsDocumentScanning.getClient(scannerOptions)

            scanner.getStartScanIntent(context.asComponentActivity())
                .addOnSuccessListener { intentSender ->
                    onSuccess(intentSender)
                }
                .addOnFailureListener { exception ->
                    onFailure(ScannerError.DocumentScannerError(message = exception.message.toString()))
                }
                .addOnCanceledListener {
                    onFailure(ScannerError.DocumentScannerCanceled(message = DOCUMENT_SCANNER_CANCELED_MESSAGE))
                }
        }
    }
    private fun Context.asComponentActivity(): ComponentActivity {
        var context = this
        while (context is ContextWrapper) {
            if (context is ComponentActivity) return context
            context = context.baseContext
        }
        throw IllegalStateException("No such ComponentActivity")
    }
}