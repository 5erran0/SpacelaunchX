package com.ginzo.commons.di

import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named

@Component(modules = [FeatureModule::class])
interface FeatureComponent {
  @Component.Factory
  interface Factory {
    fun create(): FeatureComponent
  }

  @Named("main")
  fun mainScheduler(): Scheduler
}