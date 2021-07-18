package com.jdavifranco.desafio.tokenfilmes

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDao
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDatabase
import junit.framework.Assert.assertEquals
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.Console


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    private lateinit var filmeDao: FilmeDao
    private lateinit var filmeDatabase: FilmeDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        filmeDatabase = Room.inMemoryDatabaseBuilder(context, FilmeDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        filmeDao = filmeDatabase.filmeDao
    }

    @Test
    @Throws(Exception::class)
    fun insertFilme() {
        val filme1 = Filme(1, "Velozes e furiosos", "14:00")
        val filme2 = Filme(2, "Shreck", "14:00")
        filmeDao.insert(filme1)
        filmeDao.insert(filme2)
        val res = filmeDao.getFilmeById(2)
        assertEquals("Shreck", res.title)
        

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        filmeDatabase.close()
    }

}