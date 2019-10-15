package com.ginzo.spacex_info_data.repositories

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.repository.SpaceXInfoRepository
import io.reactivex.Single
import javax.inject.Inject

class SpaceXInfoDataRepository @Inject constructor(
  private val apiRepository: SpaceXInfoApiRepository
) : SpaceXInfoRepository {
  override fun getCompanyInfo(): Single<Either<Throwable, CompanyInfo>> {
    return apiRepository.getCompanyInfo()
  }
}