package com.cmed.charactersinfo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cmed.charactersinfo.model.Character
import com.cmed.charactersinfo.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharacterRepo {

    private const val TAG = "CharacterRepo"
    val characterLiveData = MutableLiveData<List<Character>>()

    fun getCharacters(): MutableLiveData<List<Character>> {

        val call = RetrofitClient.apiInterface.getCharacters()

        call.enqueue(object: Callback<List<Character>> {
            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Log.e(TAG, "getCharacters onFailure: ", t)
            }

            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                val body = response.body()
                Log.d(TAG, "onResponse: "+ body.toString())
                characterLiveData.postValue(response.body())
            }
        })

        return characterLiveData
    }

}