package com.jdavifranco.desafio.tokenfilmes.repository

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.database.FilmeDao
import com.jdavifranco.desafio.tokenfilmes.network.FilmesApiService
import com.jdavifranco.desafio.tokenfilmes.network.FilmesNetwork
import com.jdavifranco.desafio.tokenfilmes.network.asDatabaseFilmesModel
import com.jdavifranco.desafio.tokenfilmes.network.toDetalhesDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
A classe respository servirá como fonte de dados para ser acessada por viewModels
Essa classe recebe como parametro uma referencia para Dao do banco de dados e uma
referencia para api para comunicar com o servidor criada usando a biblioteca retrofit
 */
class FilmesRepository(private val database:FilmeDao, private val networkSource:FilmesApiService) {
    /*
    Essa variável será a única fonte de verdade para o app, tanto para a ui de lista de filmes
    quanto para a de detalhes. Sempre que o app for aberto os dados são carregados do banco de dados
    e em segundo plano são atualizados com os dados da api, caso haja internet, dessa forma mantendo
     os dados disponíveis para o usuário o mais atualizados possível.
     */
    val filmes:LiveData<List<Filme>> = database.getAllFilmes()
    //variável para tratamento de erros
    private var _networkError = MutableLiveData<Boolean>(false)
    val networkError:LiveData<Boolean>
    get() = _networkError
    //Função para atualizar os dados do banco de dados com os dados do servidor
    suspend fun refreshFilmes(){
        //Utilizando coroutines para fazer a chamada a api e não bloquear a Main Thread.
        withContext(Dispatchers.IO){
            try{
                //Recebe um objeto do tipo FilmesNetowrk que possui como propriedade uma lista de filmesDto
                //e uma funcao para mapear para database filmes
                val filmesUpdated= FilmesNetwork(networkSource.getFilmes())
                database.insertAll(filmesUpdated.asDatabaseFilmesModel())
                _networkError.postValue(false)
            }catch (e:Exception){
                _networkError.postValue(true)
            }
        }
    }

    //Funcao que atualiza os detalhes de um filme por id
    suspend fun refreshDetalhesOfFilmeById(filmeId:Long) {
        //Utilizando coroutines para fazer a chamada a api e não bloquear a Main Thread.
        withContext(Dispatchers.IO) {
            try {
                val detalhesDTO = networkSource.getFilmeDetalhesById(filmeId)
                val filme = database.getFilmeById(filmeId)
                filme.detalhes = detalhesDTO.toDetalhesDatabaseModel()
                database.updateFilme(filme)
                _networkError.postValue(false)
            }catch (e:Exception){
                Log.e("Error", "${e.message}")
                _networkError.postValue(true)
            }
        }
    }
}