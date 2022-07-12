package com.pujiarto.ppid.presentation.document_listings

import com.pujiarto.ppid.domain.model.DocumentListing

data class DocumentListingsState(
    val documents: List<DocumentListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
