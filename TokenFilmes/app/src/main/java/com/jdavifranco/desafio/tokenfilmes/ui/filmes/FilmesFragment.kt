package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
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
    private lateinit var adapter: FilmesGridAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.filmes_fragment, container, false)
        adapter = FilmesGridAdapter()
        binding.filmeViewModel = viewModel
        binding.rvFilmes.adapter = adapter
        binding.rvFilmes.setHasFixedSize(true)

        viewModel.filmes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return binding.root
    }


}