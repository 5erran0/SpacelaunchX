package com.ginzo.spacex_info_data.company.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksEntity(
  @Json(name = "article_link")
  val article: String,
  @Json(name = "wikipedia")
  val wikipedia: String,
  @Json(name = "video_link")
  val video: String
)