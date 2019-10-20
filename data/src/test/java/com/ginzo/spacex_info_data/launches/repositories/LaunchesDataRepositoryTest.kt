package com.ginzo.spacex_info_data.launches.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Test

class LaunchesDataRepositoryTest {
  private val apiRepository: LaunchesApiRepository = mock()

  private val dataRepository = LaunchesDataRepository(apiRepository)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(apiRepository)
  }

  @Test
  fun getLaunches_ok() {
    dataRepository.getLaunches()

    verify(apiRepository).getLaunches()
  }

}