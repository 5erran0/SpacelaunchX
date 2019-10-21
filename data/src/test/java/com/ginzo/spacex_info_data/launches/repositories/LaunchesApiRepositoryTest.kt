package com.ginzo.spacex_info_data.launches.repositories

import arrow.core.Either
import com.ginzo.spacex_info_data.launches.api.LaunchesRestApi
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.After
import org.junit.Test
import retrofit2.Response

class LaunchesApiRepositoryTest {

  private val restApi: LaunchesRestApi = mock()

  private val apiRepository = LaunchesApiRepository(restApi)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(restApi)
  }

  @Test
  fun getLaunches_filter_ok() {
    whenever(restApi.getLaunches(any(), any(), any())).doReturn(Single.just(Response.success(emptyList())))

    apiRepository.getLaunches(LaunchesFilter(1, false, "asc"))
      .test()
      .assertValue(Either.right(emptyList()))

    verify(restApi).getLaunches(1, false, "asc")
  }
}