package com.ginzo.spacex_info_domain.entities

data class Launch(
  val flightNumber: String,
  val missionName: String,
  val launchDate: String,
  val launchTime: String,
  val daysFromLaunch: Int,
  val launchSuccess: Boolean?,
  val rocket: Rocket,
  val links: Links
)