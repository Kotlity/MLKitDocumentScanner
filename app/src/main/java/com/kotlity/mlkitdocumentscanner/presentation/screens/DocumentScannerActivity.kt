package com.kotlity.mlkitdocumentscanner.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kotlity.mlkitdocumentscanner.presentation.states.DocumentScannerPreferencesState
import com.kotlity.mlkitdocumentscanner.presentation.ui.theme.MLKitDocumentScannerTheme
import com.kotlity.mlkitdocumentscanner.presentation.viewmodels.MLKitDocumentScannerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocumentScannerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mlKitDocumentScannerViewModel by viewModels<MLKitDocumentScannerViewModel>()
            val mlKitDocumentScannerState = mlKitDocumentScannerViewModel.mlKitDocumentScannerState
            val isGalleryImportAllowed by mlKitDocumentScannerViewModel.isGalleryImportAllowed.collectAsStateWithLifecycle()
            val pageNumberLimit by mlKitDocumentScannerViewModel.pageLimit.collectAsStateWithLifecycle()
            val resultFormats by mlKitDocumentScannerViewModel.resultFormats.collectAsStateWithLifecycle()
            val scannerMode by mlKitDocumentScannerViewModel.scannerMode.collectAsStateWithLifecycle()
            val pdfFileDownloadResultFlow = mlKitDocumentScannerViewModel.pdfFileDownloadResultFlow

            MLKitDocumentScannerTheme {
                MLKitDocumentScannerScreen(
                    mlKitDocumentScannerState = mlKitDocumentScannerState,
                    documentScannerPreferencesState = DocumentScannerPreferencesState(
                        isGalleryImportAllowed = isGalleryImportAllowed,
                        pageNumberLimit = pageNumberLimit,
                        resultFormats = resultFormats,
                        scannerMode = scannerMode
                    ),
                    pdfFileDownloadResultFlow = pdfFileDownloadResultFlow,
                    onEvent = mlKitDocumentScannerViewModel::onEvent
                )
            }
        }
    }
}