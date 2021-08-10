package com.cmed.charactersinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmed.charactersinfo.model.Character
import com.cmed.charactersinfo.repository.CharacterRepo

class CharacterViewModel : ViewModel() {

    var characterLiveData: MutableLiveData<List<Character>>? = null

    fun getCharacters() : LiveData<List<Character>>? {
        characterLiveData = CharacterRepo.getCharacters()
        return characterLiveData
    }

}