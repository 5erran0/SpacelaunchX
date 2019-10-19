package com.ginzo.spacex_info_data.company.repositories

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.repository.SpaceXInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class CompanyDataRepository @Inject constructor(
  private val apiRepository: CompanyApiRepository
) : SpaceXInfoRepository {
  override fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>> {
    return apiRepository.getCompanyInfo()
  }
}