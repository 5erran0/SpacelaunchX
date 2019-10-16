package com.ginzo.spacex_info_domain.repository

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import io.reactivex.Single

interface SpaceXInfoRepository {
  fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>>
}