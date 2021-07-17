package com.jdavifranco.desafio.tokenfilmes

import android.util.Log
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApi
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApiService
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }