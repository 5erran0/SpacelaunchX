package com.ginzo.spacex_info_data.company.api

import com.ginzo.spacex_info_data.company.entities.CompanyInfoEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface CompanyRestApi {
  @GET("info")
  fun getCompanyInfo(): Single<Response<CompanyInfoEntity>>
}