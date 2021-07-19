package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import com.jdavifranco.desafio.tokenfilmes.util.TokenFilmesApplication
import kotlinx.coroutines.launch

class FilmesViewModel(private val _repository:FilmesRepository) : ViewModel() {
    val filmes = _repository.filmes
    init {
        refreshFilmesData()
        Log.e("Teste Repository", "${_repository.filmes.value?.size}")
    }
    private fun refreshFilmesData(){
        viewModelScope.launch {
            _repository.refreshFilmes()
        }
    }
}

class FilmesViewModelFactory(private val repository: FilmesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}