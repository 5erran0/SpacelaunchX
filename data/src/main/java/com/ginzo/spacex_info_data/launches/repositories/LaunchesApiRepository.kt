package com.ginzo.spacex_info_data.launches.repositories

import arrow.core.Either
import com.ginzo.commons.test.OpenClass
import com.ginzo.remote.responseToEither
import com.ginzo.spacex_info_data.launches.api.LaunchesRestApi
import com.ginzo.spacex_info_domain.entities.Launch
import io.reactivex.Single
import javax.inject.Inject

@OpenClass
class LaunchesApiRepository @Inject constructor(
  private val restApi: LaunchesRestApi
) {

  fun getLaunches(): Single<Either<Throwable, List<Launch>>> {
    return restApi.getLaunches()
      .compose(responseToEither { launches -> launches.map { it.toDomain() } })
  }

}