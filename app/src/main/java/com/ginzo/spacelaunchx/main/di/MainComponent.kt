package com.ginzo.spacelaunchx.main.di

import com.ginzo.spacelaunchx.ActivityModule
import com.ginzo.spacelaunchx.main.MainActivity
import com.ginzo.spacex_info_domain.di.DomainComponent
import dagger.Component

@Component(
  dependencies = [DomainComponent::class],
  modules = [ActivityModule::class]
)
interface MainComponent {
  @Component.Factory
  interface Factory {
    fun create(domainComponent: DomainComponent): MainComponent
  }

  fun mainActivity(): MainActivity.Component.Factory
}