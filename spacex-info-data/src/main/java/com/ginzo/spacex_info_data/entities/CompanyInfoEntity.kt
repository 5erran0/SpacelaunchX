package com.ginzo.spacex_info_data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyInfoEntity(
  @Json(name = "name")
  val companyName: String,
  val founder: String,
  @Json(name = "founded")
  val foundedYear: Int,
  @Json(name = "employees")
  val numEmployers: Int,
  @Json(name = "launch_sites")
  val launchSite: Int,
  @Json(name = "valuation")
  val valuation: Int
)