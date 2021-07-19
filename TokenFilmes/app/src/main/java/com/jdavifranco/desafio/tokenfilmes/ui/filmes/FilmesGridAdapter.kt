package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.databinding.FilmesGridItemLayoutBinding

class FilmesGridAdapter():
    ListAdapter<Filme, FilmesGridAdapter.FilmesViewHolder>(
    FilmesDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        //Cria o  binding
        val binding = FilmesGridItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context))

        return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        var item = getItem(position)
        holder.bind(item)
    }

    class FilmesViewHolder(private var binding: FilmesGridItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filme: Filme) {
            binding.filme = filme
            binding.executePendingBindings()
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