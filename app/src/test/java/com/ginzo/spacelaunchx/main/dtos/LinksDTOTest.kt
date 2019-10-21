package com.ginzo.spacelaunchx.main.dtos

import com.ginzo.spacex_info_domain.entities.Links
import org.junit.Assert.assertEquals
import org.junit.Test

class LinksDTOTest {

  @Test
  fun createDTO() {
    val dto = LinksDTO(Links("1", "2", "3", "4"))
    assertEquals("1", dto.patch)
    assertEquals("2", dto.article)
    assertEquals("3", dto.wikipedia)
    assertEquals("4", dto.video)
  }

  @Test
  fun toDomain() {
    assertEquals(
      Links("1", "2", "3", "4"),
      LinksDTO("1", "2", "3", "4").toDomain()
    )
  }
}