package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.databinding.FilmesItemLayoutBinding

/*
List Adapter para o recyclerVIew
 */

class FilmesAdapter():
    ListAdapter<Filme, FilmesAdapter.FilmesViewHolder>(
    FilmesDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        //Cria o  binding
        val binding = FilmesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context))

        return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FilmesViewHolder(private var binding: FilmesItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(filme: Filme) {
            binding.filme = filme
            clicklistener(filme.id)
            binding.executePendingBindings()

        }
        private fun clicklistener(filmeId: Long){
            binding.root.setOnClickListener {
                it.findNavController().navigate(FilmesFragmentDirections.actionFilmesFragmentToDetalhesFragment(filmeId))
            }
        }

    }

    class FilmesDiffCallBack():DiffUtil.ItemCallback<Filme>(){
        override fun areItemsTheSame(oldItem: Filme, newItem: Filme): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Filme, newItem: Filme): Boolean {
            return oldItem.detalhes == newItem.detalhes
        }

    }

}