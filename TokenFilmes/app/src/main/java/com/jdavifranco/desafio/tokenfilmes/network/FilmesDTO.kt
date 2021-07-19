package com.jdavifranco.desafio.tokenfilmes.network


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/*
Data transfer objects, essas classes tem como função receber os objetos convertidos
das chamadas http
*/

//dto que recebe a chamada http para filmes que serão exibidos na lista de filmes
@Parcelize
data class FilmesDTO(
    val id: Long,
    val title:String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "poster_url") val poster_src: String) : Parcelable

//dto que recebe a chamada http para detalhes de um filme que serão exibidos na tela de detalhes

@Parcelize
data class DetalhesDTO(
    @Json(name = "id")val filmeId:Long,
    @Json(name = "release_date") val year:String,
    val runtime:Int,
    @Json(name = "vote_average") val rating:Double,
    val popularity:Long,
    val overview:String,
    val genres:List<String>
) : Parcelable