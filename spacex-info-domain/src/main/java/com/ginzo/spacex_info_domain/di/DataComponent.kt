package com.ginzo.spacex_info_domain.di

import com.ginzo.spacex_info_domain.repository.SpaceXInfoRepository

interface DataComponent {
  fun spaceXInfoRepository(): SpaceXInfoRepository
}