package com.jdavifranco.desafio.tokenfilmes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import com.jdavifranco.desafio.tokenfilmes.ui.detalhes.DetalhesViewModel
import com.jdavifranco.desafio.tokenfilmes.ui.filmes.FilmesViewModel
/* Factory que cria um FilmeViewModel e um DetalhesViewModel com os
parametros necessários
 */
class ViewModelFactory(private val repository: FilmesRepository) : ViewModelProvider.Factory {
    private var filmeId: Long = 0
    constructor(repository: FilmesRepository, filmeId:Long):this(repository){
        this.filmeId = filmeId
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmesViewModel(repository) as T
        }
        if(modelClass.isAssignableFrom(DetalhesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalhesViewModel(repository, filmeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}