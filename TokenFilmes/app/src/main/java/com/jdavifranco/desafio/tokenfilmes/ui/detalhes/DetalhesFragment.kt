package com.jdavifranco.desafio.tokenfilmes.ui.detalhes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdavifranco.desafio.tokenfilmes.R

class DetalhesFragment : Fragment() {

    companion object {
        fun newInstance() = DetalhesFragment()
    }

    private lateinit var viewModel: DetalhesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detalhes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetalhesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}