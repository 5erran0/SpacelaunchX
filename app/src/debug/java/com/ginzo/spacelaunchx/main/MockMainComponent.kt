package com.ginzo.spacelaunchx.main

import com.ginzo.spacelaunchx.main.di.MainComponent
import com.nhaarman.mockitokotlin2.mock
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [MockModule::class])
internal interface MockMainComponent : MainComponent

@Module
internal class MockModule {

  @Provides
  fun mainPresenterProvider(): MainPresenter = mock()
}
