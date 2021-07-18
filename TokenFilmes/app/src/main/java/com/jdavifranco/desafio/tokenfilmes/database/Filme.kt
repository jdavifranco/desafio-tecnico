package com.jdavifranco.desafio.tokenfilmes.database

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
    val postUrl:String)