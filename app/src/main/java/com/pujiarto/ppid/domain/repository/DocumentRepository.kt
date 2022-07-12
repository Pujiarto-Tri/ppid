package com.pujiarto.ppid.domain.repository

import com.pujiarto.ppid.domain.model.DocumentListing
import com.pujiarto.ppid.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface DocumentRepository {

    suspend fun getDocumentListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<DocumentListing>>>
}