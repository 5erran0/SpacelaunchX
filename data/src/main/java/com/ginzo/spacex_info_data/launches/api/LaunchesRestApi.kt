package com.ginzo.spacex_info_data.launches.api

import com.ginzo.spacex_info_data.launches.entities.LaunchEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchesRestApi {
  @GET("launches")
  fun getLaunches(
    @Query("launch_year") year: Int? = null,
    @Query("launch_success") isSuccess: Boolean? = null,
    @Query("order") order: String? = null
  ): Single<Response<List<LaunchEntity>>>
}