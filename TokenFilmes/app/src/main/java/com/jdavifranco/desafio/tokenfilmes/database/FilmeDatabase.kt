package com.jdavifranco.desafio.tokenfilmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Essa será a classe Database que herda de Room,
será responsável por guardar ou criar uma instancia do Banco de dados caso
nao exista. É um singleton.
 */

@Database(entities = [Filme::class], version = 1, exportSchema = false)
abstract class FilmeDatabase : RoomDatabase(){
    /*
    Conecta a database ao DAO.
   */
    abstract val filmeDao:FilmeDao

    /**
     * Define um companion object para adicionar funções de acesso ao banco de dados
     */
    companion object {
        /**
         * INSTANCE irá manter uma referencia para a database criada.
         *
         *  A anotação @Volatile serve para identificar que o valor dessa variável
         *  nao sera guardado em cache e qualquer alteração a ela deve ser exposta para
         *  todas as threads.
         */
        @Volatile
        private var INSTANCE: FilmeDatabase? = null

        fun getInstance(context: Context): FilmeDatabase {
            /*
            Para ter certeza que a database é criada apenas uma vez, usa-se o bloco synchronized
            que permite que apenas uma thread acesse o bloco por vez
             */
            synchronized(this) {

                // copia o valor para uma var normal para poder usar o smart cast
                var instance = INSTANCE

                // se a instance for null, cria uma nova database e guarda em instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilmeDatabase::class.java,
                        "filmes_database"
                    ).fallbackToDestructiveMigration().build()
                    // INSTANCE recebe a database criada
                    INSTANCE = instance
                }

                // Retorna a instance pois dessa forma com o smart cast, garante que será nao nula
                return instance
            }
        }
    }
}