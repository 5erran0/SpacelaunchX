package com.ginzo.spacex_info_data

import com.ginzo.remote.RemoteComponent
import com.ginzo.spacex_info_domain.di.SpaceXInfoDataComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  dependencies = [RemoteComponent::class],
  modules = [SpaceXInfoDataModule::class]
)
interface SpaceXInfoDataComponentImpl : SpaceXInfoDataComponent {
  @Component.Factory
  interface Factory {
    fun create(remoteComponent: RemoteComponent): SpaceXInfoDataComponent
  }
}