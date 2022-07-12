package com.pujiarto.ppid.data.csv

import com.opencsv.CSVReader
import com.pujiarto.ppid.domain.model.DocumentListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.util.stream.Stream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentListingsParser @Inject constructor(): CSVParser<DocumentListing> {

    override suspend fun parse(stream: InputStream): List<DocumentListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val nama_dinas = line.getOrNull(0)
                    val judul_dip = line.getOrNull(1)
                    val file = line.getOrNull(2)
                    val penanggungjawab = line.getOrNull(3)
                    DocumentListing(
                        judul_dip = judul_dip ?: return@mapNotNull null,
                        nama_dinas = nama_dinas ?: return@mapNotNull null,
                        file = file ?: return@mapNotNull null,
                        penanggungjawab = penanggungjawab ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}