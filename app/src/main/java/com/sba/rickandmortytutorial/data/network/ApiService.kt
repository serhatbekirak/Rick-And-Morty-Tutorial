package com.sba.rickandmortytutorial.data.network

import com.sba.rickandmortytutorial.data.model.CharacterWrapper
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("character")
  suspend fun getCharacters(): Response<CharacterWrapper>
}