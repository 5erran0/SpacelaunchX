package com.ginzo.spacex_info_domain.usecases

import arrow.core.Either
import com.ginzo.commons.test.OpenClass
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.repository.CompanyRepository
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class GetCompanyInfoUseCase @Inject constructor(
  private val repository: CompanyRepository
) {
  fun companyInfo(): Single<Either<Throwable, CompanyInfo>> {
    return repository.getCompanyInfo()
  }
}