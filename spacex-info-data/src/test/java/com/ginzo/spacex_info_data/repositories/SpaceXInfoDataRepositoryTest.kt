package com.ginzo.spacex_info_data.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Test

class SpaceXInfoDataRepositoryTest {

  private val apiRepository: SpaceXInfoApiRepository = mock()

  private val dataRepository = SpaceXInfoDataRepository(apiRepository)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(dataRepository)
  }

  @Test
  fun getCompanyInfo() {
    dataRepository.getCompanyInfo()

    verifyNoMoreInteractions(apiRepository)
  }
}
