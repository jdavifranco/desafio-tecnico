package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdavifranco.desafio.tokenfilmes.R
import com.jdavifranco.desafio.tokenfilmes.databinding.FilmesFragmentBinding
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesApiStatus
import com.jdavifranco.desafio.tokenfilmes.ui.ViewModelFactory
import com.jdavifranco.desafio.tokenfilmes.util.TokenFilmesApplication

class FilmesFragment : Fragment() {

    companion object {
        fun newInstance() = FilmesFragment()
    }
    //Criando ViewModel
    private val viewModel by lazy {
        ViewModelProvider(
        this, ViewModelFactory(TokenFilmesApplication.repository)
        )
        .get(FilmesViewModel::class.java)}

    //Data binding
    private lateinit var binding:FilmesFragmentBinding
    //adapter para o recyclerView
    private lateinit var adapter: FilmesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.filmes_fragment, container, false)
        //tratando resultados offline
        viewModel.networkResult.observe(viewLifecycleOwner, Observer {
            when(it){
                FilmesApiStatus.ERROR -> binding.errorMessage.visibility = View.VISIBLE
                else -> binding.errorMessage.visibility = View.GONE
            }
        })
        binding.btnAtualizarFilmes.setOnClickListener {
            viewModel.refresh()
        }

        adapter = FilmesAdapter()
        binding.filmeViewModel = viewModel
        binding.rvFilmes.adapter = adapter
        binding.rvFilmes.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        binding.rvFilmes.setHasFixedSize(true)
        binding.lifecycleOwner = this
        //Utilizando LiveData para observar os estado da lista de fimes no viewModel e atualizar
        //sempre que houver mudanças
        viewModel.filmes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })



        return binding.root
    }

}