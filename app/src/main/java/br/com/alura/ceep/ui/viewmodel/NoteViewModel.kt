package br.com.alura.ceep.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.ceep.model.Note
import br.com.alura.ceep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
) : ViewModel() {

    suspend fun findAll(): List<Note> = withContext(IO) { repository.findAll() }

}