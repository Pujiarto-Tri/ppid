package com.pujiarto.ppid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DocumentListingEntity(
    val judul_dip: String,
    val nama_dinas: String,
    val file: String,
    val penanggungjawab: String,
    @PrimaryKey val id: Int? = null
)
