package com.pujiarto.ppid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DocumentListingEntity::class],
    version = 1
)
abstract class DocumentDatabase: RoomDatabase() {
    abstract val dao: DocumentDao
}