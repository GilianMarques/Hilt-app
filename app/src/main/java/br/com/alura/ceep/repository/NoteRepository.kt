package br.com.alura.ceep.repository

import android.util.Log
import br.com.alura.ceep.database.dao.NoteDao
import br.com.alura.ceep.model.Note
import br.com.alura.ceep.retrofit.service.NoteService
import javax.inject.Inject

// para injetar dependencias que sao interfaces, é necessario criar modulos para ensinar ao hilt como instanciar um objeto
class NoteRepository @Inject constructor(
    private val dao: NoteDao,
    private val service: NoteService,
) {

    suspend fun findAll(): List<Note> {
        try {
            dao.save(service.findAll())

        } catch (e: Exception) {
            Log.e("NoteRepository", "findAll: failure to fetch new notes from API")
        }
        return dao.findAll()
    }

}