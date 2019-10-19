package com.ginzo.spacex_info_data.repositories

import arrow.core.Either
import com.ginzo.commons.feature_commons.test.OpenClass
import com.ginzo.remote.responseToEither
import com.ginzo.spacex_info_data.api.SpaceXInfoRestApi
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class SpaceXInfoApiRepository @Inject constructor(
  private val restApi: SpaceXInfoRestApi
) {

  fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>> {
    return restApi.getCompanyInfo()
      .compose(responseToEither { it.toDomain() })
  }
}