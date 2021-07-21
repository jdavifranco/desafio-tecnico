package com.jdavifranco.desafio.tokenfilmes.ui.detalhes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import kotlinx.coroutines.launch

class DetalhesViewModel(private val _repository: FilmesRepository, private val filmeId:Long) : ViewModel() {
    val networkError = liveData<Boolean> { _repository.networkError }
   val filme = _repository.getFilmeById(filmeId, viewModelScope)

}