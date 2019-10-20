package com.ginzo.spacex_info_domain.di

import com.ginzo.spacex_info_domain.repository.CompanyRepository
import com.ginzo.spacex_info_domain.repository.LaunchesRepository

interface DataComponent {
  fun companyRepository(): CompanyRepository
  fun launchesRepository(): LaunchesRepository
}