package com.ginzo.spacex_info_data.api

import com.ginzo.spacex_info_data.entities.CompanyInfoEntity
import com.ginzo.spacex_info_data.entities.LaunchEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXInfoRestApi {
  @GET("info")
  fun getCompanyInfo(): Single<Response<CompanyInfoEntity>>

  @GET("launches")
  fun getLaunches(): Single<Response<List<LaunchEntity>>>
}