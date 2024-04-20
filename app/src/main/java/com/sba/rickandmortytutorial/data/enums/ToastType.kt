package com.sba.rickandmortytutorial.data.enums

import com.sba.rickandmortytutorial.R

enum class ToastType(val style: Int) {
  Success(R.drawable.ic_success),
  Error(R.drawable.ic_error),
  Warning(R.drawable.ic_warning),
  Info(R.drawable.ic_info)
}