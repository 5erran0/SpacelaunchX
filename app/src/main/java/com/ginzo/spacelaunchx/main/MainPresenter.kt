package com.ginzo.spacelaunchx.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import arrow.core.getOrElse
import com.ginzo.commons.test.OpenClass
import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import com.ginzo.spacex_info_domain.entities.Links
import com.ginzo.spacex_info_domain.usecases.GetCompanyInfoUseCase
import com.ginzo.spacex_info_domain.usecases.GetLaunchesUseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject
import javax.inject.Named

@OpenClass
class MainPresenter @Inject constructor(
  private val view: MainView,
  private val getCompanyInfoUseCase: GetCompanyInfoUseCase,
  private val getLaunchesUseCase: GetLaunchesUseCase,
  @Named("main") private val main: Scheduler
) : DefaultLifecycleObserver {

  private val disposable = CompositeDisposable()
  private var filter: LaunchesFilter = LaunchesFilter(null, null, null)

  override fun onCreate(owner: LifecycleOwner) {
    getSpaceXInformation()
  }

  override fun onDestroy(owner: LifecycleOwner) {
    disposable.dispose()
  }

  fun getSpaceXInformation() {
    disposable.addAll(Flowable.combineLatest(
      getCompanyInfoUseCase.companyInfo().map {
        it.getOrElse {
          null
        }
      }.toFlowable(),
      getLaunchesUseCase.launches().map { it.getOrElse { emptyList() } }.toFlowable(),
      BiFunction { companyInfo: CompanyInfo?, launches: List<Launch> ->
        if (companyInfo != null && launches.isNotEmpty()) {
          MainViewState.ShowInformation(SpaceXInformationDTO(companyInfo, launches))
        } else {
          MainViewState.Error
        }
      })
      .observeOn(main)
      .startWith(MainViewState.Loading)
      .subscribe(view::render) { view.render(MainViewState.Error) }
    )
  }

  fun onClickLaunchItem(links: Links) {
    view.render(MainViewState.ShowLinksDialog(links))
  }

  fun onClickFilter() {
    view.render(MainViewState.ShowFilterDialog(filter))
  }

  fun onFilterLaunches(filter: LaunchesFilter) {
    this.filter = filter

    disposable.addAll(
      getLaunchesUseCase.launches(filter)
        .map { either ->
          either.fold(
            { MainViewState.Error },
            { MainViewState.ShowLaunches(it) }
          )
        }
        .toFlowable()
        .observeOn(main)
        .startWith(MainViewState.Loading)
        .subscribe(view::render) { view.render(MainViewState.Error) }
    )
  }
}