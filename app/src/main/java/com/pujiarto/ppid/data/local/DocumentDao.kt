package com.pujiarto.ppid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocumentListings(
        documentListingEntities: List<DocumentListingEntity>
    )

    @Query("DELETE FROM documentlistingentity")
    suspend fun clearDocumentListings()

    @Query(
        """
            SELECT *
            FROM documentlistingentity
            WHERE LOWER(judul_dip) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == nama_dinas
        """
    )
    suspend fun searchDocumentListing(query: String): List<DocumentListingEntity>
}