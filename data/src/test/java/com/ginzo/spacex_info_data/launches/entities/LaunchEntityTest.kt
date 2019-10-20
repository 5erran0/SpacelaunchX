package com.ginzo.spacex_info_data.launches.entities

import com.ginzo.spacex_info_domain.entities.Launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit

class LaunchEntityTest {

  @Test
  fun toDomain() {
    val localDate = LocalDateTime.now()

    assertEquals(
      Launch(
        flightNumber = "1",
        missionName = "2",
        launchDate = localDate.toLocalDate().toString(),
        launchTime = localDate.toLocalTime().toString(),
        daysFromLaunch = ChronoUnit.DAYS.between(LocalDate.now(), localDate).toInt(),
        launchSuccess = true,
        rocket = RocketEntity("3", "4", "5").toDomain(),
        links = LinksEntity("6", "7", "8", "9").toDomain()
      ),
      LaunchEntity(
        flightNumber = "1",
        missionName = "2",
        launchDate = localDate,
        launchSuccess = true,
        rocket = RocketEntity("3", "4", "5"),
        links = LinksEntity("6", "7", "8", "9")
      ).toDomain()
    )
  }
}