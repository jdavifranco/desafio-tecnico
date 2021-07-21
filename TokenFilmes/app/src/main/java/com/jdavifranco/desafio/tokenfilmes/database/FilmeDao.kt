package com.jdavifranco.desafio.tokenfilmes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filmes: List<Filme>)

    @Update
    suspend fun updateFilme(filme: Filme)

    @Query("DELETE FROM table_filme")
    suspend fun deleteAll()

    @Query("SELECT * FROM table_filme")
    fun getAllFilmes():List<Filme>

    @Query(value = "select * from table_filme where id= :id")
    suspend fun getFilmeById(id: Long):Filme
}