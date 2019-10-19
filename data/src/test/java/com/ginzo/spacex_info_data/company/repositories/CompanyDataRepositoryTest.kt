package com.ginzo.spacex_info_data.company.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Test

class CompanyDataRepositoryTest {

  private val apiRepository: CompanyApiRepository = mock()

  private val dataRepository = CompanyDataRepository(apiRepository)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(apiRepository)
  }

  @Test
  fun getCompanyInfo() {
    dataRepository.getCompanyInfo()

    verify(apiRepository).getCompanyInfo()
  }
}
