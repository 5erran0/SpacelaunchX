package com.ginzo.spacex_info_data.launches.entities

import com.ginzo.spacex_info_domain.entities.Launch
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit

@JsonClass(generateAdapter = true)
data class LaunchEntity(
  @Json(name = "flight_number")
  val flightNumber: String,
  @Json(name = "mission_name")
  val missionName: String,
  @Json(name = "launch_date_utc")
  val launchDate: LocalDateTime,
  @Json(name = "launch_success")
  val launchSuccess: Boolean?,
  val rocket: RocketEntity,
  val links: LinksEntity
) {
  fun toDomain(): Launch {
    return Launch(
      flightNumber = flightNumber,
      missionName = missionName,
      launchDate = launchDate.toLocalDate().toString(),
      launchTime = launchDate.toLocalTime().toString(),
      daysFromLaunch = ChronoUnit.DAYS.between(LocalDate.now(), launchDate).toInt(),
      launchSuccess = launchSuccess,
      rocket = rocket.toDomain(),
      links = links.toDomain()
    )
  }
}