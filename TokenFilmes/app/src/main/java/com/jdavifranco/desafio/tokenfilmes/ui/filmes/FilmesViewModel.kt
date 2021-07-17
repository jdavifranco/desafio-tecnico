package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesViewModel : ViewModel() {
    var _response = ""

    fun getFilmes():String{
        FilmesApi.retrofitService.getFilmes().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response = response.body().toString()
            }
        })
        return _response
    }
}