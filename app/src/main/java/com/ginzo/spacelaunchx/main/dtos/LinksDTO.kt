package com.ginzo.spacelaunchx.main.dtos

import android.os.Parcelable
import com.ginzo.spacex_info_domain.entities.Links
import kotlinx.android.parcel.Parcelize

@Parcelize
class LinksDTO(
  val patch: String?,
  val article: String?,
  val wikipedia: String?,
  val video: String?
) : Parcelable {

  constructor(links: Links) : this(links.patch, links.article, links.wikipedia, links.video)

  fun toDomain(): Links {
    return Links(patch, article, wikipedia, video)
  }
}