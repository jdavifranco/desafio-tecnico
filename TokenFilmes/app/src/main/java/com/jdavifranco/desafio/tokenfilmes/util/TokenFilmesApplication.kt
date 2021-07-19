package com.jdavifranco.desafio.tokenfilmes.util

import android.app.Application
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDatabase
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApiService
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesRepository
import retrofit2.Retrofit

/*
Essa classe herda da classe padrão Application do android
para que sejam criados o banco de dados e o FilmesApi a partir
do contexto da aplicação.
 */
class TokenFilmesApplication :Application() {
    private val database by lazy { FilmeDatabase.getInstance(this).filmeDao }
    private val retrofitApiService by lazy {
        FilmesApiService.getRetrofit().create(FilmesApiService::class.java)
    }

    companion object{
        lateinit var repository: FilmesRepository
    }

    override fun onCreate() {
        super.onCreate()
        repository = FilmesRepository(database, retrofitApiService)
    }

}