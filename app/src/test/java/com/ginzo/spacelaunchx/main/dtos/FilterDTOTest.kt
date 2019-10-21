package com.ginzo.spacelaunchx.main.dtos

import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import org.junit.Assert
import org.junit.Test

class FilterDTOTest {
  @Test
  fun createDTO() {
    val dto = FilterDTO(LaunchesFilter(2019, true, "asc"))
    Assert.assertEquals(2019, dto.year)
    Assert.assertEquals(true, dto.isSuccess)
    Assert.assertEquals("asc", dto.order)
  }

  @Test
  fun toDomain() {
    Assert.assertEquals(
      LaunchesFilter(2019, true, "asc"),
      FilterDTO(2019, true, "asc").toDomain()
    )
  }
}