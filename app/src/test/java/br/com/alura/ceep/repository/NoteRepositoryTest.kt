package br.com.alura.ceep.repository

import br.com.alura.ceep.database.dao.NoteDao
import br.com.alura.ceep.model.Note
import br.com.alura.ceep.retrofit.service.NoteService
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NoteRepositoryTest : TestCase() {

    @Test
    fun testeQualquer() = runBlocking {

        class MockNoteDao : NoteDao {
            override suspend fun save(notes: List<Note>) {

            }

            override fun findAll(): List<Note> {
                return ArrayList<Note>()
            }

            override fun countAll(): Int {
                return 10
            }


        }


        class MockNoteService : NoteService {
            override suspend fun findAll(): List<Note> {
                return ArrayList()
            }

        }

        val repo = NoteRepository(MockNoteDao(), MockNoteService())

        assertEquals(0, repo.findAll())
    }

}