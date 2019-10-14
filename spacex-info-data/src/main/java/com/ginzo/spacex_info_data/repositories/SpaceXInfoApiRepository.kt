package com.ginzo.spacex_info_data.repositories

import arrow.core.Either
import com.ginzo.spacex_info_data.api.SpaceXInfoRestApi
import io.reactivex.Single
import javax.inject.Inject

class SpaceXInfoApiRepository @Inject constructor(
  private val restApi: SpaceXInfoRestApi
) {

  fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>>
}