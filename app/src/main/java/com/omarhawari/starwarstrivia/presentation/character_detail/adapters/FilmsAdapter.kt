package com.omarhawari.starwarstrivia.presentation.character_detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omarhawari.starwarstrivia.BR
import com.omarhawari.starwarstrivia.R
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.presentation.films.components.mapEpisodeIdToImage

class FilmsAdapter(private val films: ArrayList<Film>) :
    RecyclerView.Adapter<BindableViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_film,
            parent,
            false
        )
        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount() = films.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Film> = arrayListOf()) {
        films.clear()
        films.addAll(items)
        notifyDataSetChanged()
    }

}

class BindableViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        println("HEREEREREEREREER")
        binding.setVariable(BR.film, film)
        binding.setVariable(BR.imageId, mapEpisodeIdToImage[film.episodeId])
    }
}

@BindingAdapter("films")
fun bindFilms(recyclerView: RecyclerView, films: List<Film>) {
    val adapter = getOrCreateAdapter(recyclerView, films)
    adapter.updateItems(films)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView, films: List<Film>): FilmsAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is FilmsAdapter) {
        recyclerView.adapter as FilmsAdapter
    } else {
        val bindableRecyclerAdapter = FilmsAdapter(films = ArrayList(films))
        recyclerView.adapter = bindableRecyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
        bindableRecyclerAdapter
    }
}

