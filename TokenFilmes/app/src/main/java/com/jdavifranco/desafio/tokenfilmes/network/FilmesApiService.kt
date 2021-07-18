package com.jdavifranco.desafio.tokenfilmes.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/*
Interface que contém as chamadas para a api, essas chamadas serão feitas
 utilizando a biblioteca retrofit: https://square.github.io/retrofit/
A biblioteca retrofit cria as chamadas http com base nos parâmetros
passados: A Url Base e um ConverterFactory  que deve saber como converter
os dados recebidos para objetos kotlin
 */
//Apenas um método que requisita todos os filmes
interface FilmesApiService {
    @GET("movies")
    suspend fun getFilmes(): List<FilmesNetwork>

    companion object{
        /*
        Create moshi object para ser usado pelo retrofit como converter
        Moshi é uma biblioteca json que facilita a conversao de json para objetos java
        https://github.com/square/moshi
        */
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        //Base Url
        private const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"
        //criando o objeto retrofit

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.github.com/")
            .build()

        // retrofit service criado utlizando a interface FilmesApiService
        val retrofitService : FilmesApiService by lazy {
            retrofit.create(FilmesApiService::class.java)
        }

    }

}



