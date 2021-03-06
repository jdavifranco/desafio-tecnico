package com.jdavifranco.desafio.tokenfilmes.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Essa classe com a anotação @Entity servirá para identificar
os valores de cada coluna da tabela "table_filme".
 */

@Entity(tableName = "table_filme")
data class Filme(
    @PrimaryKey
    val id:Long,
    val title:String,
    val postUrl:String,
    val year:String,
    val vote:Double,
    @Embedded var detalhes:Detalhes?
    )

data class Detalhes(
    val runtime: Int?,
    val popularity: Double?,
    val overview: String?,
    val genres: String?,
)