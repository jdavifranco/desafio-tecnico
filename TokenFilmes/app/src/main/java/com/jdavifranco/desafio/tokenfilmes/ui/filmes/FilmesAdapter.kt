package com.jdavifranco.desafio.tokenfilmes.ui.filmes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.databinding.FilmesItemLayoutBinding

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

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [MarsProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [MarsProperty]
     */
    class OnClickListener(val clickListener: (filme:Filme) -> Unit) {
        fun onClick(filme: Filme) = clickListener(filme)
    }

}