package com.jdavifranco.desafio.tokenfilmes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmeDao {
    @Insert
    fun insert(filme: Filme)

    @Query("DELETE FROM table_filme")
    fun deleteAll()

    @Query("SELECT * FROM table_filme")
    fun getAllFilmes():List<Filme>

    @Query("select * from table_filme where id = :id")
    fun getFilmeById(id:Long):Filme

    @Update
    fun updateFilmeDetalhes(filme: Filme)
}