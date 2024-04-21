package com.sba.rickandmortytutorial.data.repository

import android.content.Context
import com.sba.rickandmortytutorial.data.SBAResult
import com.sba.rickandmortytutorial.data.model.CharacterWrapper
import com.sba.rickandmortytutorial.data.network.toResultFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val dataSource: CharacterDataSource) {
  suspend fun getCharacterList(pageIndex: Int, context: Context): Flow<SBAResult<CharacterWrapper>> {
    return toResultFlow(context) {
      dataSource.getCharacters(pageIndex)
    }
  }
}