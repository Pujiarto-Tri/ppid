package com.pujiarto.ppid.di

import com.pujiarto.ppid.data.csv.CSVParser
import com.pujiarto.ppid.data.csv.DocumentListingsParser
import com.pujiarto.ppid.data.repository.DocumentRepositoryImpl
import com.pujiarto.ppid.domain.model.DocumentListing
import com.pujiarto.ppid.domain.repository.DocumentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDocumentListingsParser(
        documentListingsParser: DocumentListingsParser
    ): CSVParser<DocumentListing>

    @Binds
    @Singleton
    abstract fun bindDocumentRepository(
        documentRepositoryImpl: DocumentRepositoryImpl
    ): DocumentRepository
}