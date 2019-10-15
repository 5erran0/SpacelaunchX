package com.ginzo.spacex_info_domain.entities

data class CompanyInfo(
  val companyName: String,
  val founder: String,
  val foundedYear: Int,
  val numEmployers: Int,
  val launchSite: Int,
  val valuation: Int
)