package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jdavifranco.desafio.tokenfilmes.R

class FilmesFragment : Fragment() {

    companion object {
        fun newInstance() = FilmesFragment()
    }

    private lateinit var viewModel: FilmesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.filmes_fragment, container, false)
        val resp:TextView = view.findViewById(R.id.txtRetrofit)
        resp.text = viewModel.getFilmes()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmesViewModel::class.java)
        // TODO: Use the ViewModel

    }

}