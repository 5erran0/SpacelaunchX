package com.ginzo.spacex_info_domain.repository

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import io.reactivex.Single

interface LaunchesRepository {
  fun getLaunches(filter: LaunchesFilter? = null): Single<Either<Throwable, List<Launch>>>
}