package com.ginzo.commons.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

@Module
abstract class FeatureModule {
  @Module
  companion object {
    @Provides
    @JvmStatic
    @Named("main")
    fun schedulerMainProvider(): Scheduler {
      return AndroidSchedulers.mainThread()
    }
  }
}