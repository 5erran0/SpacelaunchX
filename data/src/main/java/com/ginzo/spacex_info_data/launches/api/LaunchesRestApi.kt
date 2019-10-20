package com.ginzo.spacex_info_data.launches.api

import com.ginzo.spacex_info_data.launches.entities.LaunchEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface LaunchesRestApi {
  @GET("launches")
  fun getLaunches(): Single<Response<List<LaunchEntity>>>
}