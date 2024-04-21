package com.sba.rickandmortytutorial.data.repository

import com.sba.rickandmortytutorial.data.network.ApiService
import javax.inject.Inject

class CharacterDataSource @Inject constructor(private val apiService: ApiService) {
  suspend fun getCharacters() = apiService.getCharacters()
}