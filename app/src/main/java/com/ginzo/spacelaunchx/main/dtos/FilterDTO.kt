package com.ginzo.spacelaunchx.main.dtos

import android.os.Parcelable
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilterDTO(
  val year: Int? = null,
  val isSuccess: Boolean? = null,
  val order: String? = null
) : Parcelable {

  constructor(filter: LaunchesFilter) : this(filter.year, filter.isSuccess, filter.order)

  fun toDomain(): LaunchesFilter {
    return LaunchesFilter(year, isSuccess, order)
  }
}