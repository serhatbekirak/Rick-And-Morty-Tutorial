package com.sba.rickandmortytutorial.data.network

import com.sba.rickandmortytutorial.data.model.CharacterWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
  @GET("character")
  suspend fun getCharacters(@Query("page") pageNumber: Int): Response<CharacterWrapper>
}