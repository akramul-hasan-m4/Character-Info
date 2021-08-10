package com.cmed.charactersinfo.network

import retrofit2.Call
import retrofit2.http.GET
import com.cmed.charactersinfo.model.Character

interface ApiInterface {

    @GET("characters")
    fun getCharacters() : Call<List<Character>>

}