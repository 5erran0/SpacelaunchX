package com.ginzo.spacelaunchx.main

import androidx.lifecycle.LifecycleOwner
import arrow.core.Either
import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.usecases.GetCompanyInfoUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class MainPresenterTest {
  private val useCase: GetCompanyInfoUseCase = mock()
  private val view: MainView = mock()

  private val presenter = MainPresenter(view, useCase, Schedulers.trampoline())

  private val owner: LifecycleOwner = mock()

  private val companyInfo = CompanyInfo(
    "tesla",
    "elon musk",
    2000,
    3, 3,
    2000000
  )

  @After
  fun tearDown() {
    verifyNoMoreInteractions(useCase)
  }

  @Test
  fun onCreate_ok() {
    whenever(useCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(useCase).companyInfo()
    verify(view).render(MainViewState.ShowInformation(SpaceXInformationDTO(companyInfo)))
  }

  @Test
  fun onCreate_ko() {
    val throwable = Throwable()
    whenever(useCase.companyInfo()).thenReturn(Single.just(Either.left(throwable)))

    presenter.onCreate(owner)

    verify(view).render(MainViewState.Loading)
    verify(useCase).companyInfo()
    verify(view).render(MainViewState.Error)
  }

  @Test
  fun getSpaceXInformation_ok() {
    whenever(useCase.companyInfo()).thenReturn(Single.just(Either.right(companyInfo)))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(useCase).companyInfo()
    verify(view).render(MainViewState.ShowInformation(SpaceXInformationDTO(companyInfo)))
  }

  @Test
  fun getSpaceXInformation_ko() {
    val throwable = Throwable()
    whenever(useCase.companyInfo()).thenReturn(Single.just(Either.left(throwable)))

    presenter.getSpaceXInformation()

    verify(view).render(MainViewState.Loading)
    verify(useCase).companyInfo()
    verify(view).render(MainViewState.Error)
  }
}