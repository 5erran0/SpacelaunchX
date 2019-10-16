package com.ginzo.remote.moshi.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime

class LocalDateTimeAdapter {
  @FromJson
  fun fromJson(string: String) = LocalDateTime.parse(string)

  @ToJson
  fun toJson(value: LocalDateTime) = value.toString()
}