package com.ginzo.spacex_info_domain.di

import dagger.Component

@Component(dependencies = [SpaceXInfoDataComponent::class])
interface SpaceXInfoDomainComponent {
  @Component.Factory
  interface Factory {
    fun create(spaceXInfoDataComponent: SpaceXInfoDataComponent): SpaceXInfoDomainComponent
  }
}