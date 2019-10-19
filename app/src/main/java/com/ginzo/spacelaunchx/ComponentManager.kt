package com.ginzo.spacelaunchx

import com.ginzo.remote.DaggerRemoteComponentImpl
import com.ginzo.remote.RemoteComponent
import com.ginzo.spacelaunchx.main.di.DaggerMainComponent
import com.ginzo.spacelaunchx.main.di.MainComponent
import com.ginzo.spacex_info_data.DaggerDataComponentImpl
import com.ginzo.spacex_info_domain.di.DaggerDomainComponent
import com.ginzo.spacex_info_domain.di.DataComponent
import com.ginzo.spacex_info_domain.di.DomainComponent

class ComponentManager {
  private val remoteComponent: RemoteComponent by lazy {
    DaggerRemoteComponentImpl.factory().create()
  }

  private val dataComponent: DataComponent by lazy {
    DaggerDataComponentImpl.factory().create(remoteComponent)
  }

  private val domainComponent: DomainComponent by lazy {
    DaggerDomainComponent.factory().create(dataComponent)
  }

  internal val mainComponent: MainComponent by lazy {
    DaggerMainComponent.factory().create(domainComponent)
  }
}