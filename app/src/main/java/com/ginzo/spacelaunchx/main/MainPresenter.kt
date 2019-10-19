package com.ginzo.spacelaunchx.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ginzo.commons.test.OpenClass
import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.usecases.GetCompanyInfoUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@OpenClass
class MainPresenter @Inject constructor(
  private val view: MainView,
  private val getCompanyInfoUseCase: GetCompanyInfoUseCase,
  @Named("main") private val main: Scheduler
) : DefaultLifecycleObserver {

  private val disposable = CompositeDisposable()

  override fun onCreate(owner: LifecycleOwner) {
    getSpaceXInformation()
  }

  override fun onDestroy(owner: LifecycleOwner) {
    disposable.dispose()
  }

  fun getSpaceXInformation() {
    disposable.addAll(getCompanyInfoUseCase.companyInfo()
      .map { either ->
        either.fold(
          { MainViewState.Error },
          { companyInformation -> MainViewState.ShowInformation(SpaceXInformationDTO(companyInformation)) }
        )
      }
      .toFlowable()
      .observeOn(main)
      .startWith(MainViewState.Loading)
      .subscribe(view::render) { MainViewState.Error }
    )
  }

}