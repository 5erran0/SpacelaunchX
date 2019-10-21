package com.ginzo.spacelaunchx.main.dtos

import com.ginzo.spacex_info_domain.entities.Links
import org.junit.Assert.assertEquals
import org.junit.Test

class LinksDTOTest {

  @Test
  fun createDTO() {
    assertEquals(
      LinksDTO("1", "2", "3", "4"),
      LinksDTO(Links("1", "2", "3", "4"))
    )
  }

  @Test
  fun toDomain() {
    assertEquals(
      Links("1", "2", "3", "4"),
      LinksDTO("1", "2", "3", "4").toDomain()
    )
  }
}