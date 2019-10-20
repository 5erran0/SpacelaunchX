package com.ginzo.spacex_info_data.launches.entities

import com.ginzo.spacex_info_domain.entities.Links
import org.junit.Assert.assertEquals
import org.junit.Test

class LinksEntityTest {

  @Test
  fun toDomain() {
    assertEquals(
      Links("6", "7", "8", "9"),
      LinksEntity("6", "7", "8", "9").toDomain()
    )
  }
}