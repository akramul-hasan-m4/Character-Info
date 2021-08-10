package com.cmed.charactersinfo.view

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cmed.charactersinfo.R
import com.cmed.charactersinfo.adapter.CharacterAdapter
import com.cmed.charactersinfo.databinding.ActivityMainBinding
import com.cmed.charactersinfo.databinding.DialogCharacterDetailsBinding
import com.cmed.charactersinfo.model.Character
import com.cmed.charactersinfo.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity(), CharacterAdapter.CharacterListener {

    private lateinit var context: Context
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@MainActivity
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        characterViewModel.getCharacters()!!.observe(this, { characters ->

            val adapter = CharacterAdapter(characters, context, this)
            binding.rvCharacters.adapter = adapter
            binding.progressBar.visibility = View.GONE

        })
    }

    override fun onCharacterItemClicked(character: Character) {
        showCharacterDetails(character)
    }

    private fun showCharacterDetails(character: Character) {
        val builder = AlertDialog.Builder(context)
        dialogBinding = DialogCharacterDetailsBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val dialog = builder.create()

        dialogBinding.tvName.text = character.name
        dialogBinding.tvSpecies.text = character.species
        dialogBinding.tvGender.text = character.gender
        dialogBinding.tvHouse.text = character.house
        dialogBinding.tvDob.text = character.dateOfBirth
        dialogBinding.tvYob.text = character.yearOfBirth
        dialogBinding.tvAncestry.text = character.ancestry
        dialogBinding.tvEyeColor.text = character.eyeColour
        dialogBinding.tvHairColor.text = character.hairColour
        dialogBinding.tvActor.text = character.actor
        dialogBinding.tvWandCore.text = character.wand.core
        Glide.with(context).load(character.image).apply(RequestOptions.circleCropTransform()).into(dialogBinding.ivCharacterImg)

        dialogBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}