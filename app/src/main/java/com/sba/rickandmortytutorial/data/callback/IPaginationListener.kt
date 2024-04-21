package com.sba.rickandmortytutorial.data.callback

interface IPaginationListener {
  var currentPageIndex: Int
  var hasMorePage: Boolean
  var onLoadMoreAction: () -> Unit
}