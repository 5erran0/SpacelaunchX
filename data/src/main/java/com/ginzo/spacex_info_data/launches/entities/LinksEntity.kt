package com.ginzo.spacex_info_data.launches.entities

import com.ginzo.spacex_info_domain.entities.Links
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksEntity(
  @Json(name = "mission_patch")
  val missionPatch: String,
  @Json(name = "article_link")
  val article: String,
  @Json(name = "wikipedia")
  val wikipedia: String,
  @Json(name = "video_link")
  val video: String
) {
  fun toDomain(): Links {
    return Links(missionPatch, article, wikipedia, video)
  }
}