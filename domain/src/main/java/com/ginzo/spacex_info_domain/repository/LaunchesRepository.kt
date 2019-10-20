package com.ginzo.spacex_info_domain.repository

import arrow.core.Either
import com.ginzo.spacex_info_domain.entities.Launch
import io.reactivex.Single

interface LaunchesRepository {
  fun getLaunches(): Single<Either<Throwable, List<Launch>>>
}