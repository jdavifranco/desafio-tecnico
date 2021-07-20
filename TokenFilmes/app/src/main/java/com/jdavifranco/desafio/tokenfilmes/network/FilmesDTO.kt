package com.jdavifranco.desafio.tokenfilmes.network


import android.os.Parcelable
import com.jdavifranco.desafio.tokenfilmes.database.Detalhes
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
Data transfer objects, essas classes tem como função receber a resposta da api e converter para
objetos database
*/

//dto para a lista de filmes que será recebida pela api
data class FilmesNetwork(val filmes:List<FilmesDTO>)
//Extension Function para converter mapear os objetos para Objetos do banco de dados (Filme)
fun FilmesNetwork.asDatabaseFilmesModel():  List<Filme> {
    return filmes.map {
        Filme(
            id = it.id,
            title = it.title,
            postUrl= it.posterUrl,
            year = it.year,
            vote=it.vote,
            detalhes = null)
    }
}
//dto para os dados simples dos filmes
@JsonClass(generateAdapter = true)
data class FilmesDTO(
    val id: Long,
    val title:String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "poster_url") val posterUrl: String,
    @Json(name = "release_date") val year:String,
    @Json(name = "vote_average") val vote:Double)


//dto para os detalhes dos filmes
@JsonClass(generateAdapter = true)
data class DetalhesDTO(
    var runtime:Int,
    val popularity:Long,
    val overview:String,
    val genres:String
)


//extension function para mapear os Detalhes recebidos da api do filme para o modelo do banco de dados
fun DetalhesDTO.toDetalhesDatabaseModel() = Detalhes(
    runtime = runtime,
    popularity = popularity,
    overview = overview,
    genres = genres
)