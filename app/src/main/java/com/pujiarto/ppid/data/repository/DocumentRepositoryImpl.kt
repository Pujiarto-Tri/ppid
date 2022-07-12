package com.pujiarto.ppid.data.repository

import com.pujiarto.ppid.data.csv.CSVParser
import com.pujiarto.ppid.data.local.DocumentDatabase
import com.pujiarto.ppid.data.mapper.toDocumentListing
import com.pujiarto.ppid.data.mapper.toDocumentListingEntity
import com.pujiarto.ppid.data.remote.DocumentApi
import com.pujiarto.ppid.domain.model.DocumentListing
import com.pujiarto.ppid.domain.repository.DocumentRepository
import com.pujiarto.ppid.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRepositoryImpl @Inject constructor(
    val api: DocumentApi,
    val db: DocumentDatabase,
    val documentListingsParser: CSVParser<DocumentListing>
): DocumentRepository  {

    private val dao = db.dao

    override suspend fun getDocumentListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<DocumentListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchDocumentListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toDocumentListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val loadFromCache = !isDbEmpty && !fetchFromRemote
            if(loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                documentListingsParser.parse(response.byteStream())
            } catch (e: IOException ){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load PPID data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load PPID data"))
                null
            }
            remoteListings?.let { listings ->
                dao.clearDocumentListings()
                dao.insertDocumentListings(
                    listings.map { it.toDocumentListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchDocumentListing("")
                        .map { it.toDocumentListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}