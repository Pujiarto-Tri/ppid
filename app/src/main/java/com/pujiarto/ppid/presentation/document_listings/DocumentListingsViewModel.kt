package com.pujiarto.ppid.presentation.document_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pujiarto.ppid.domain.repository.DocumentRepository
import com.pujiarto.ppid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentListingsViewModel @Inject constructor(
    private val repository: DocumentRepository
): ViewModel() {

    var state by mutableStateOf(DocumentListingsState())

    private var searchJob: Job? = null

    init {
        getDocumentListings()
    }

    fun onEvent(event: DocumentListingsEvent) {
        when(event) {
            is DocumentListingsEvent.Refresh -> {
                getDocumentListings(fetchFromRemote = true)
            }
            is DocumentListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getDocumentListings()
                }
            }
        }
    }

    private fun getDocumentListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getDocumentListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    documents = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}