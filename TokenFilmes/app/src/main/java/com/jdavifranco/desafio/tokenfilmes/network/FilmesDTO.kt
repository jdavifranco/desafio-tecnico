package com.jdavifranco.desafio.tokenfilmes.network

import com.squareup.moshi.Json
/*
Data transfer object, essa é uma classe cuja função é armazenar os dados recebidos
através das chamadas http para api.
id": 19404,
      "vote_average": 9.3,
      "title": "Dilwale Dulhania Le Jayenge",
      "poster_url": "https://image.tmdb.org/t/p/w200/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg",
      "genres": [
        "Comedy",
        "Drama",
        "Romance"
      ],
      "release_date": "1995-10-20"
 */
data class FilmesNetwork(
    val id: String,
    val vote_average:Double,
    val title:String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "poster_src") val poster_url: String,
    val genres: List<String>,
    val release_date: String)