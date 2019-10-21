package com.ginzo.spacelaunchx.main

import androidx.lifecycle.LifecycleOwner
import arrow.core.Either
import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.entities.*
import com.ginzo.spacex_info_domain.entities.LaunchesFilter.Companion.ASC
import com.ginzo.spacex_info_domain.usecases.GetCompanyInfoUseCase
import com.ginzo.spacex_info_domain.usecases.GetLaunchesUseCase
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class MainPresenterTest {
  private val getCompanyInfoUseCase: GetCompanyInfoUseCase = mock()
  private val getLaunchesUseCase: GetLaunchesUseCase = mock()
  private val view: MainView = mock()

  private val presenter =
    MainPresenter(view, getCompanyInfoUseCase, getLaunchesUseCase, Schedulers.trampoline())

  private val owner: LifecycleOwner = mock()

  private val companyInfo = CompanyInfo(
    "tesla",
    "elon musk",
    2000,
    3, 3,
    2000000
  )

  private val launches = listOf(
    Launch(
      flightNumber = "1",
      missionName = "2",
      launchDate = "aaaa/mm/dd",
      launchTime = "hh:mm",
      daysFromLaunch = 10,
      launchSuccess = true,
      rocket = Rocket("3", "4", "5"),
      links = Links("6", "7", "8", "9")
    )
  )

  @After
  fun tearDown() {
    verifyNoMoreInteractions(getCompanyInfoUseCase, getLaunchesUseCase)
  }

  @Test
  fun onCreate_ok() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.right(launches)))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.ShowInformation(SpaceXInformationDTO(companyInfo, launches)))
  }

  @Test
  fun onCreate_getCompanyKo() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.left(Throwable())))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.right(launches)))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun onCreate_getLaunchesKo() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.left(Throwable())))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun onCreate_Ko() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.left(Throwable())))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.left(Throwable())))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun getSpaceXInformation_ok() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.right(launches)))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.ShowInformation(SpaceXInformationDTO(companyInfo, launches)))
  }

  @Test
  fun getSpaceXInformation_getCompanyKo() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.left(Throwable())))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.right(launches)))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun getSpaceXInformation_getLaunchesKo() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.left(Throwable())))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun getSpaceXInformation_Ko() {
    whenever(getCompanyInfoUseCase.companyInfo()).thenReturn(Single.just(Either.left(Throwable())))
    whenever(getLaunchesUseCase.launches()).thenReturn(Single.just(Either.left(Throwable())))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(getCompanyInfoUseCase).companyInfo()
    verify(getLaunchesUseCase).launches()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun showLinksDialog() {
    presenter.onClickLaunchItem(launches[0].links)

    verify(view).render(MainViewState.ShowLinksDialog(launches[0].links))
  }

  @Test
  fun showFilterDialog() {
    val filter = LaunchesFilter(null, null, null)
    presenter.onClickFilter()

    verify(view).render(MainViewState.ShowFilterDialog(filter))
  }

  @Test
  fun onFilter_ok() {
    val filter = LaunchesFilter(null, null, ASC)
    whenever(getLaunchesUseCase.launches(any())).thenReturn(Single.just(Either.right(launches)))

    presenter.onFilterLaunches(filter)

    verify(view).render(MainViewState.Loading)
    verify(getLaunchesUseCase).launches(filter)
    verify(view).render(MainViewState.ShowLaunches(launches))
  }

  @Test
  fun onFilter_ko() {
    val filter = LaunchesFilter(null, null, ASC)
    whenever(getLaunchesUseCase.launches(any())).thenReturn(Single.just(Either.left(Throwable())))

    presenter.onFilterLaunches(filter)

    verify(view).render(MainViewState.Loading)
    verify(getLaunchesUseCase).launches(filter)
    verify(view).render(MainViewState.Error)
  }
}