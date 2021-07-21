package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import com.jdavifranco.desafio.tokenfilmes.ui.detalhes.DetalhesViewModel
import com.jdavifranco.desafio.tokenfilmes.util.TokenFilmesApplication
import kotlinx.coroutines.launch

class FilmesViewModel(private val _repository:FilmesRepository) : ViewModel() {
    //Variavel LiveData recebida do repositorio
    val filmes:LiveData<List<Filme>>
    get() = _repository.getFilmes(viewModelScope)


    val networkError = liveData<Boolean> { _repository.networkError }

}