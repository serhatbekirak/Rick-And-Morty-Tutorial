package com.sba.rickandmortytutorial.data

class SBAException(
  val errorMessage: String?,
  val errorCode: Int?,
  val customObject: Any?
) : Exception(errorMessage)