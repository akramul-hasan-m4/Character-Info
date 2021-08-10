package com.cmed.charactersinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cmed.charactersinfo.databinding.ItemCharacterBinding
import com.cmed.charactersinfo.model.Character

class CharacterAdapter(val characters : List<Character> , val context : Context, val listener : CharacterListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    interface CharacterListener{
        fun onCharacterItemClicked(character : Character)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {

        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.tvName.text = character.name
        holder.binding.tvSpecies.text = character.species
        holder.binding.tvGender.text = character.gender

        Glide.with(holder.itemView.context).load(character.image).apply(RequestOptions.circleCropTransform()).into(holder.binding.ivCharacterImg)

        holder.itemView.setOnClickListener {
            listener.onCharacterItemClicked(character)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {}

}

