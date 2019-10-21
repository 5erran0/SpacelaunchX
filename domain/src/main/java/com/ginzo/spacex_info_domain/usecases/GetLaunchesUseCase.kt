package com.ginzo.spacex_info_domain.usecases

import arrow.core.Either
import com.ginzo.commons.test.OpenClass
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import com.ginzo.spacex_info_domain.repository.LaunchesRepository
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class GetLaunchesUseCase @Inject constructor(
  private val repository: LaunchesRepository
) {
  fun launches(filter: LaunchesFilter? = null): Single<Either<Throwable, List<Launch>>> {
    return repository.getLaunches(filter)
  }
}