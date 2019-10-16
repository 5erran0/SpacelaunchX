package com.ginzo.spacelaunchx.main

import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO

interface MainView {
  fun render(state: MainViewState)

}

sealed class MainViewState {
  object Loading : MainViewState()
  object Error : MainViewState()
  data class ShowInformation(val information: SpaceXInformationDTO) : MainViewState()
}