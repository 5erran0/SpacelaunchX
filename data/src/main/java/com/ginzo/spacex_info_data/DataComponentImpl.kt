package com.ginzo.spacex_info_data

import com.ginzo.remote.RemoteComponent
import com.ginzo.spacex_info_domain.di.DataComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  dependencies = [RemoteComponent::class],
  modules = [DataModule::class]
)
interface DataComponentImpl : DataComponent {
  @Component.Factory
  interface Factory {
    fun create(remoteComponent: RemoteComponent): DataComponent
  }
}