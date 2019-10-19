package com.ginzo.spacex_info_data.company.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class LaunchEntity(
  @Json(name = "flight_number")
  val flightNumber: String,
  @Json(name = "mission_name")
  val missionName: String,
  @Json(name = "launch_date_local")
  val launchDate: LocalDateTime,
  @Json(name = "launch_success")
  val launchSuccess: Boolean,
  val rocket: RocketEntity,
  val links: LinksEntity
)