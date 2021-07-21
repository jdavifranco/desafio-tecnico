package com.jdavifranco.desafio.tokenfilmes.ui.detalhes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jdavifranco.desafio.tokenfilmes.R
import com.jdavifranco.desafio.tokenfilmes.databinding.DetalhesFragmentBinding
import com.jdavifranco.desafio.tokenfilmes.ui.ViewModelFactory
import com.jdavifranco.desafio.tokenfilmes.ui.filmes.FilmesViewModel
import com.jdavifranco.desafio.tokenfilmes.util.TokenFilmesApplication

class DetalhesFragment : Fragment() {

    companion object {
        fun newInstance() = DetalhesFragment()
    }

    private val args:DetalhesFragmentArgs by navArgs()
    //Criando ViewModel
    private lateinit var viewModel :DetalhesViewModel


    //Data binding Variaveis
    private lateinit var binding: DetalhesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detalhes_fragment, container, false)
        viewModel = ViewModelProvider(
            this, ViewModelFactory(TokenFilmesApplication.repository, args.filmeId)
        ).get(DetalhesViewModel::class.java)
        viewModel.filme.observe(viewLifecycleOwner, Observer {
            binding.filme = it
        })

        return binding.root
    }


}