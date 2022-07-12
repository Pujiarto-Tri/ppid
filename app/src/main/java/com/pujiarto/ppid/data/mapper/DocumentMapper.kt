package com.pujiarto.ppid.data.mapper

import com.pujiarto.ppid.data.local.DocumentListingEntity
import com.pujiarto.ppid.domain.model.DocumentListing

fun DocumentListingEntity.toDocumentListing(): DocumentListing {
    return DocumentListing(
        judul_dip = judul_dip,
        nama_dinas = nama_dinas,
        file = file,
        penanggungjawab = penanggungjawab
    )
}
fun DocumentListing.toDocumentListingEntity(): DocumentListingEntity {
    return DocumentListingEntity(
        judul_dip = judul_dip,
        nama_dinas = nama_dinas,
        file = file,
        penanggungjawab = penanggungjawab
    )
}