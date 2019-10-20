package com.ginzo.spacex_info_data.launches.repositories

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.repository.LaunchesRepository
import io.reactivex.Single
import javax.inject.Inject

class LaunchesDataRepository @Inject constructor(
  private val apiRepository: LaunchesApiRepository
) : LaunchesRepository {

  override fun getLaunches(): Single<Either<Throwable, List<Launch>>> {
    return apiRepository.getLaunches()
  }
}