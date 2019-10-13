package com.ginzo.remote

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class])
interface RemoteComponentImpl : RemoteComponent {
  @Component.Factory
  interface Factory {
    fun create(): RemoteComponent
  }
}

interface RemoteComponent {
  fun retrofit(): Retrofit
}