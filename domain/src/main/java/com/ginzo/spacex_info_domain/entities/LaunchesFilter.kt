package com.ginzo.spacex_info_domain.entities

data class LaunchesFilter(
  val year: Int? = null,
  val isSuccess: Boolean? = null,
  val order: String?
) {

  companion object {
    const val ASC = "asc"
    const val DESC = "desc"
  }
}


