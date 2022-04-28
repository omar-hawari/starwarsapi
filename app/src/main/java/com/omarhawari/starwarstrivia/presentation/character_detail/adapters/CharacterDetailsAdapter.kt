package com.omarhawari.starwarstrivia.presentation.character_detail.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omarhawari.starwarstrivia.BR
import com.omarhawari.starwarstrivia.R

class CharacterDetailsAdapter(private val characterDetails: ArrayList<Pair<String, String>>) :
    RecyclerView.Adapter<BindableViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder2 {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_character_details,
            parent,
            false
        )
        return BindableViewHolder2(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder2, position: Int) {
        holder.bind(characterDetails[position])
    }

    override fun getItemCount() = characterDetails.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Pair<String, String>> = arrayListOf()) {
        characterDetails.clear()
        characterDetails.addAll(items)
        notifyDataSetChanged()
    }

}

class BindableViewHolder2(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(characterDetail: Pair<String, String>) {
        binding.setVariable(BR.key, characterDetail.first)
        binding.setVariable(BR.value, characterDetail.second)
    }
}

@BindingAdapter("characterDetails")
fun bindCharacterDetails(recyclerView: RecyclerView, characterDetails: List<Pair<String, String>>) {
    val adapter = getOrCreateAdapter(recyclerView, characterDetails)
    adapter.updateItems(characterDetails)
}

private fun getOrCreateAdapter(
    recyclerView: RecyclerView,
    characterDetails: List<Pair<String, String>>
): CharacterDetailsAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is CharacterDetailsAdapter) {
        recyclerView.adapter as CharacterDetailsAdapter
    } else {
        val bindableRecyclerAdapter =
            CharacterDetailsAdapter(characterDetails = ArrayList(characterDetails))
        recyclerView.adapter = bindableRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        bindableRecyclerAdapter
    }
}

