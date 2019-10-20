package com.ginzo.spacex_info_data.launches.entities

import com.ginzo.spacex_info_domain.entities.Rocket
import org.junit.Assert.assertEquals
import org.junit.Test

class RocketEntityTest {

  @Test
  fun toDomain() {
    assertEquals(
      Rocket("3", "4", "5"),
      RocketEntity("3", "4", "5").toDomain()
    )
  }
}