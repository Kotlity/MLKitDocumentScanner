package com.kotlity.mlkitdocumentscanner.di.modules

import android.content.Context
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.GalleryImportAllowedManager
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.PageLimitManager
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.ResultFormatsManager
import com.kotlity.mlkitdocumentscanner.data.repositories_impl.ScannerModeManager
import com.kotlity.mlkitdocumentscanner.di.qualifiers.PageLimitQualifier
import com.kotlity.mlkitdocumentscanner.di.qualifiers.ResultFormatsQualifier
import com.kotlity.mlkitdocumentscanner.di.qualifiers.ScannerModeQualifier
import com.kotlity.mlkitdocumentscanner.domain.repositories.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesManagerModule {

    @Provides
    @Singleton
    fun provideGalleryImportAllowedManager(@ApplicationContext context: Context): PreferencesManager<Boolean> =
        GalleryImportAllowedManager(context = context)

    @Provides
    @Singleton
    @PageLimitQualifier
    fun providePageLimitManager(@ApplicationContext context: Context): PreferencesManager<Int> =
        PageLimitManager(context = context)

    @Provides
    @Singleton
    @ResultFormatsQualifier
    fun provideResultFormatsManager(@ApplicationContext context: Context): PreferencesManager<Int> =
        ResultFormatsManager(context = context)

    @Provides
    @Singleton
    @ScannerModeQualifier
    fun provideScannerModeManager(@ApplicationContext context: Context): PreferencesManager<Int> =
        ScannerModeManager(context = context)
}