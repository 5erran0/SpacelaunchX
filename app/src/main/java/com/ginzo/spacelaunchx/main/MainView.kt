package com.ginzo.spacelaunchx.main

import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import com.ginzo.spacex_info_domain.entities.Links

interface MainView {
  fun render(state: MainViewState)

}

sealed class MainViewState {
  object Loading : MainViewState()
  object Error : MainViewState()
  data class ShowInformation(val information: SpaceXInformationDTO) : MainViewState()
  data class ShowLaunches(val launches: List<Launch>) : MainViewState()
  data class ShowLinksDialog(val links: Links) : MainViewState()
  data class ShowFilterDialog(val filter: LaunchesFilter) : MainViewState()
}