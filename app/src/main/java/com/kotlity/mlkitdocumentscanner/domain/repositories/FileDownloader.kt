package com.kotlity.mlkitdocumentscanner.domain.repositories

import android.net.Uri
import com.kotlity.mlkitdocumentscanner.helpers.response.Error
import com.kotlity.mlkitdocumentscanner.helpers.response.Response
import com.kotlity.mlkitdocumentscanner.helpers.response.Success
import kotlinx.coroutines.flow.Flow

interface FileDownloader<D: Success, E: Error> {

    fun downloadFile(name: String, fileUri: Uri): Flow<Response<D, E>>
}