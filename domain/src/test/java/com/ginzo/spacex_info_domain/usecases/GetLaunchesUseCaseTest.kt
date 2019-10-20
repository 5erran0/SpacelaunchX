package com.ginzo.spacex_info_domain.usecases

import com.ginzo.spacex_info_domain.repository.LaunchesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.After
import org.junit.Test

class GetLaunchesUseCaseTest {
  private val repository: LaunchesRepository = mock()

  private val useCase = GetLaunchesUseCase(repository)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(repository)
  }

  @Test
  fun getLaunches_ok() {
    useCase.launches()

    verify(repository).getLaunches()
  }
}