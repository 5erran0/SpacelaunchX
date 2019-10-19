package com.ginzo.spacex_info_data.company.repositories

import arrow.core.Either
import com.ginzo.commons.test.OpenClass
import com.ginzo.remote.responseToEither
import com.ginzo.spacex_info_data.company.api.CompanyRestApi
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class CompanyApiRepository @Inject constructor(
  private val restApi: CompanyRestApi
) {

  fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>> {
    return restApi.getCompanyInfo()
      .compose(responseToEither { it.toDomain() })
  }
}