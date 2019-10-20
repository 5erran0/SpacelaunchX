package com.ginzo.remote.moshi.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

class LocalDateTimeAdapter {
  @FromJson
  fun fromJson(string: String) = LocalDateTime.parse(string, ISO_OFFSET_DATE_TIME)

  @ToJson
  fun toJson(value: LocalDateTime) = value.toString()
}