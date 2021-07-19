package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jdavifranco.desafio.tokenfilmes.R
import com.jdavifranco.desafio.tokenfilmes.databinding.FilmesFragmentBinding
import com.jdavifranco.desafio.tokenfilmes.util.TokenFilmesApplication

class FilmesFragment : Fragment() {

    companion object {
        fun newInstance() = FilmesFragment()
    }
    //Criando ViewModel
    private val viewModel by lazy {
        ViewModelProvider(
        this, FilmesViewModelFactory(TokenFilmesApplication.repository))
        .get(FilmesViewModel::class.java)}
    //Data binding
    private lateinit var binding:FilmesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.filmes_fragment, container, false)



        return view
    }


}