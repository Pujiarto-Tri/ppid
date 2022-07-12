package com.pujiarto.ppid.presentation.document_listings

sealed class DocumentListingsEvent {
    object Refresh: DocumentListingsEvent()
    data class OnSearchQueryChange(val query: String): DocumentListingsEvent()
}
