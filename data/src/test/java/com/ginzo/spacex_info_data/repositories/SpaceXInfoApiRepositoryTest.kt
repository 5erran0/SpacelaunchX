package com.ginzo.spacex_info_data.repositories

import arrow.core.Either
import com.ginzo.spacex_info_data.api.SpaceXInfoRestApi
import com.ginzo.spacex_info_data.entities.CompanyInfoEntity
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.After
import org.junit.Test
import retrofit2.Response

class SpaceXInfoApiRepositoryTest {

  private val restApi: SpaceXInfoRestApi = mock()
  private val companyInfoEntity = CompanyInfoEntity("tesla", "elon musk", 2000, 3, 3, 2000000)

  private val apiRepository = SpaceXInfoApiRepository(restApi)

  @After
  fun tearDown() {
    verifyNoMoreInteractions(restApi)
  }

  @Test
  fun getCompanyInfo_ok() {
    whenever(restApi.getCompanyInfo()).doReturn(Single.just(Response.success(companyInfoEntity)))

    apiRepository.getCompanyInfo()
      .test()
      .assertValue(Either.right(companyInfoEntity.toDomain()))

    verify(restApi).getCompanyInfo()
  }
}