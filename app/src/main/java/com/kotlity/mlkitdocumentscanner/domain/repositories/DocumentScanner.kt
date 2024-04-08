package com.kotlity.mlkitdocumentscanner.domain.repositories

import android.content.Context
import com.kotlity.mlkitdocumentscanner.helpers.response.Error

interface DocumentScanner<I, E: Error> {

    suspend fun scanDocument(
        galleryImportAllowed: Boolean,
        pageLimit: Int,
        resultFormats: Int,
        scannerMode: Int,
        context: Context,
        onSuccess: (I) -> Unit,
        onFailure: (E) -> Unit
    )
}