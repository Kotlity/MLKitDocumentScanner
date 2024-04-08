package com.kotlity.mlkitdocumentscanner.helpers.response

sealed interface Response<out D: Success, out E: Error> {
    data class Success<out D: com.kotlity.mlkitdocumentscanner.helpers.response.Success, out E: com.kotlity.mlkitdocumentscanner.helpers.response.Error>(val data: D): Response<D, E>
    data class Error<out D: com.kotlity.mlkitdocumentscanner.helpers.response.Success, out E: com.kotlity.mlkitdocumentscanner.helpers.response.Error>(val errorMessage: E): Response<D, E>
    class Loading<out D: com.kotlity.mlkitdocumentscanner.helpers.response.Success, out E: com.kotlity.mlkitdocumentscanner.helpers.response.Error>: Response<D, E>
}