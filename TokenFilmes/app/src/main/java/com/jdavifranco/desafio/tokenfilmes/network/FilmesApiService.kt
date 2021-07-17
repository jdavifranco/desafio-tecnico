package com.jdavifranco.desafio.tokenfilmes.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


/*
Interface que contém as chamadas para a api, essas chamadas serão feitas
 utilizando a biblioteca retrofit.
A biblioteca retrofit cria as chamadas http com base nos parâmetros
passados: A Url Base e um ConverterFactory  que deve saber como converter
os dados recebidos para objetos kotlin
 */
//Apenas um método que requisita todos os filmes
interface FilmesApiService {
    @GET("movies")
    fun getFilmes(): Call<String>
}
//Base Url
private const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"
//criando o objeto retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl("https://api.github.com/")
    .build()

// public object que servirá para acesso a api no app
object FilmesApi {
    val retrofitService : FilmesApiService by lazy {
        retrofit.create(FilmesApiService::class.java)
    }
}


