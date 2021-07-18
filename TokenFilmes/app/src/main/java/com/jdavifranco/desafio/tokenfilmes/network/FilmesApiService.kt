package com.jdavifranco.desafio.tokenfilmes.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/*
Interface que contém as chamadas para a api, essas chamadas serão feitas
 utilizando a biblioteca retrofit: https://square.github.io/retrofit/
A biblioteca retrofit cria as chamadas http com base nos parâmetros
passados: A Url Base e um ConverterFactory  que deve saber como converter
os dados recebidos para objetos kotlin
 */
//Apenas um método que requisita todos os filmes
//O objeto que implementa
interface FilmesApiService {
    @GET("movies")
    suspend fun getFilmes(): List<FilmesNetwork>

    @GET("movies")
    suspend fun getFilmeDetailById(@Query("id")id:String):FilmesNetwork
}

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
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

/*Singleton object pois apenas uma instancia do retrofitservice estara disponível
para acesso a api
 */
object FilmesApi{
    //by lazy significa que será inicializado somente quando for necessário
    val retrofitService : FilmesApiService by lazy {
        retrofit.create(FilmesApiService::class.java)
    }

}


