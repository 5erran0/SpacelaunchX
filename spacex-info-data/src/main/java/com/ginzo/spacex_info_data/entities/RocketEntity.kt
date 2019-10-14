package com.ginzo.spacex_info_data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketEntity(
  @Json(name = "rocket_id")
  val id: String,
  @Json(name = "rocket_name")
  val name: String,
  @Json(name = "rocket_type")
  val type: String
)