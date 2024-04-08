package com.kotlity.mlkitdocumentscanner.presentation.states

import android.content.IntentSender
import com.kotlity.mlkitdocumentscanner.helpers.response.ScannerError

data class ScannerResult(
    val intentSender: IntentSender? = null,
    val scannerError: ScannerError? = null
)
