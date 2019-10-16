package com.ginzo.spacex_info_domain.di

import dagger.Component

@Component(dependencies = [DataComponent::class])
interface DomainComponent {
  @Component.Factory
  interface Factory {
    fun create(dataComponent: DataComponent): DomainComponent
  }
}