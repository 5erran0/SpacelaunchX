package com.ginzo.spacelaunchx

import android.app.Application
import com.ginzo.spacelaunchx.main.di.MainComponent
import com.ginzo.spacelaunchx.main.di.MainComponentProvider

class SpacelaunchXApplication : Application(), MainComponentProvider {

  private lateinit var componentManager: ComponentManager

  override fun onCreate() {
    super.onCreate()
    componentManager = ComponentManager()
  }

  override val mainComponent: MainComponent
    get() = componentManager.mainComponent
}