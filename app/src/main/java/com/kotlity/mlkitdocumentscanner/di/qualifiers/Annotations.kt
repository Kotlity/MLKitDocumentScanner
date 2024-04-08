package com.kotlity.mlkitdocumentscanner.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PageLimitQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ResultFormatsQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScannerModeQualifier