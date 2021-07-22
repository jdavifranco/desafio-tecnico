package com.jdavifranco.desafio.tokenfilmes.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jdavifranco.desafio.tokenfilmes.R
import com.jdavifranco.desafio.tokenfilmes.database.Filme
import com.jdavifranco.desafio.tokenfilmes.repository.FilmesApiStatus
import com.jdavifranco.desafio.tokenfilmes.ui.detalhes.DetalhesViewModel
import com.jdavifranco.desafio.tokenfilmes.ui.filmes.FilmesViewModel
import java.time.Year


/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
@BindingAdapter("detailImage")
fun bindDetailImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }

}
@BindingAdapter("filmeInfo")
fun bindFilmeInfo(textView: TextView, filme:Filme){
    val info = "${filme.year.subSequence(0,4)} - ${filme.vote}"
    textView.text = info
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: FilmesApiStatus?) {
    when (status) {
        FilmesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FilmesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FilmesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatusDetalhes")
fun bindStatusDetail(statusImageView: ImageView, viewModel: DetalhesViewModel?) {
    when (viewModel?.networkResult?.value) {
        FilmesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FilmesApiStatus.ERROR -> {
            if(viewModel.filme?.value==null) {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_connection_error)
            }
            else{
                statusImageView.visibility = View.GONE
            }
        }
        FilmesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
//DetailInfo, VOte, Popularity, genres
@BindingAdapter("detailInfo")
fun bindDetailInfo(textView: TextView, filme: Filme?){
    textView.text = "${filme?.year?.subSequence(0, 4)} - ${filme?.detalhes?.runtime} min"
}
@BindingAdapter("voteInfo")
fun bindVote(textView: TextView, filme: Filme?){
    textView.text = "${filme?.vote}"
}
@BindingAdapter("popInfo")
fun bindPopularity(textView: TextView, filme: Filme?){
    textView.text = "${filme?.detalhes?.popularity}"
}
@BindingAdapter("generoInfo")
fun bindGenres(textView: TextView, filme: Filme?){
    textView.text = "Gêneros: ${filme?.detalhes?.genres}"
}