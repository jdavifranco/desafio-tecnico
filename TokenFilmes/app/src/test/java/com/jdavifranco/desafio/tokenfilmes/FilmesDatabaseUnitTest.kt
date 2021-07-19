package com.jdavifranco.desafio.tokenfilmes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jdavifranco.desafio.tokenfilmes.database.Detalhes
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDao
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDatabase
import okio.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// TODO: Adicionar mais testes para o banco de dados

/*
Testes do banco de dados Room.
as funcoões anotadas com @Before e @After são responsáveis por criar um banco de dados temporário
para teste e fechá-lo. A funcões anotadas com @Test são testes utilizando o banco de dados.
 */

/*
Para realizar os testes na máquina host ao invés de utilizar um aparelho físico ou emulador
foi utilizado o Framework para testes Roboeletric.
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class FilmesDatabaseUnitTest {
    private lateinit var filmeDao: FilmeDao
    private lateinit var filmeDatabase: FilmeDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Cria um in-memoy database pois os dados adicionados aqui devem ser destruidos quando o database for fechado
        filmeDatabase = Room.inMemoryDatabaseBuilder(context, FilmeDatabase::class.java)
            // Permitir consultas na thread principal para teste.
            .allowMainThreadQueries()
            .build()
        filmeDao = filmeDatabase.filmeDao
    }
    /*
    Teste simples em que são adicionados dois filmes ao banco de dados e depois é feita uma
    consulta para checar se os filmes foram adicionados.
     */
    @Test
    @Throws(Exception::class)
    fun insertFilme() {
        val filme1 = Filme(1, "Velozes e furiosos", "14:00", null)
        val filme2 = Filme(2, "Shreck", "14:00", null)
        filmeDao.insert(filme1)
        filmeDao.insert(filme2)
        val res = filmeDao.getFilmeById(2)
        Assert.assertEquals("Shreck", res.title)
    }

    /*
    Teste do método update da Dao para garantir que os detalhes serão atualizados
    no banco de dados, quando o método for chamado.
     */
    @Test
    @Throws(Exception::class)
    fun insertDetalhesToFilme() {
        val filme1 = Filme(1, "Velozes e furiosos", "14:00", null)
        val filme2 = Filme(2, "Shreck", "14:00", null)
        filmeDao.insert(filme1)
        filmeDao.insert(filme2)
        var res = filmeDao.getFilmeById(2)
        res.detalhes = Detalhes("2002", 120, 9.30, 123,
            "Sinopse do filme", "Animacao, Comedia, infantil")
        filmeDao.updateFilmeDetalhes(res)
        Assert.assertEquals("2002", filmeDao.getFilmeById(2).detalhes?.year)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        filmeDatabase.close()
    }

}