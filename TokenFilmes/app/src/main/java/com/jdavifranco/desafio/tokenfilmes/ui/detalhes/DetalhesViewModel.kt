package com.jdavifranco.desafio.tokenfilmes.ui.detalhes

import android.util.Log
import androidx.lifecycle.*
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import kotlinx.coroutines.launch

class DetalhesViewModel(private val _repository: FilmesRepository, private val filmeId:Long) : ViewModel() {
    val networkResult = _repository.apiStatus
    private var _filme = MutableLiveData<Filme>()
    val filme:LiveData<Filme>
    get() = _filme
    init {
        resfreshDetalhesFilme(filmeId)
    }
    //função para atualizar os dados de um filme, chamada para api
    fun resfreshDetalhesFilme(id:Long){
        viewModelScope.launch {
            _repository.refreshDetalhesOfFilmeById(id)
            _filme.postValue(_repository.getFilmeByID(id))
        }
    }



}