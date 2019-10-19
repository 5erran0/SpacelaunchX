package com.ginzo.spacelaunchx

import android.app.Application
import com.ginzo.spacelaunchx.main.DaggerMockMainComponent
import com.ginzo.spacelaunchx.main.di.MainComponent
import com.ginzo.spacelaunchx.main.di.MainComponentProvider

class MockApp : Application(), MainComponentProvider {
  override val mainComponent: MainComponent
    get() = DaggerMockMainComponent.create()
}