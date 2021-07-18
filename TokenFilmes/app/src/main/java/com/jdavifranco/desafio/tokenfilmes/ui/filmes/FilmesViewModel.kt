package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApi
import kotlinx.coroutines.launch

class FilmesViewModel : ViewModel() {

    init {
        viewModelScope.launch {
           val filmes =  FilmesApi.retrofitService.getFilmes()
            Log.e("Teste getDETAIL", "Return: ${filmes[0].title}")
        }
    }
}