package com.ginzo.spacex_info_data.company.entities

import com.ginzo.spacex_info_domain.entities.CompanyInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class CompanyInfoEntityTest {

  @Test
  fun toDomain() {
    assertEquals(
      CompanyInfo("tesla", "elon musk", 2000, 3, 3, 2000000),
      CompanyInfoEntity(
        "tesla",
        "elon musk",
        2000,
        3,
        3,
        2000000
      ).toDomain()
    )
  }
}