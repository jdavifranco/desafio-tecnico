package com.jdavifranco.desafio.tokenfilmes.util

import android.app.Application
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDatabase
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApiService
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository

/*
Essa classe herda da classe padrão Application do android
para que sejam criados o banco de dados e o FilmesApi a partir
do contexto da aplicação.

 */
class TokenFilmesApplication :Application() {
    private val database by lazy {
        FilmeDatabase.getInstance(applicationContext)
    }
    private val retrofitApiService by lazy {
        FilmesApiService.getRetrofit().create(FilmesApiService::class.java)
    }

    private val _repository  by lazy {
        FilmesRepository(database.filmeDao, retrofitApiService)
    }
    //Função getter para o acesso ao repository
    fun getRepository():FilmesRepository = _repository

}