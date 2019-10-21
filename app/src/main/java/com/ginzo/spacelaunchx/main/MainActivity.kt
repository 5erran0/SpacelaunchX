package com.ginzo.spacelaunchx.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginzo.commons.view.bindView
import com.ginzo.spacelaunchx.R
import com.ginzo.spacelaunchx.main.adapter.LaunchesListAdapter
import com.ginzo.spacelaunchx.main.di.inject
import com.ginzo.spacelaunchx.main.dialog.FilterDialog
import com.ginzo.spacelaunchx.main.dialog.LinksDialog
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, FilterDialog.FilterDialogListener, Toolbar.OnMenuItemClickListener {

  @Inject
  lateinit var presenter: MainPresenter

  private val toolbar: Toolbar by bindView(R.id.main_toolbar)
  private val infoContainer: NestedScrollView by bindView(R.id.main_information_container)
  private val companyInfo: TextView by bindView(R.id.main_company_info)
  private val launchesList: RecyclerView by bindView(R.id.main_launches)
  private val progressBar: ProgressBar by bindView(R.id.main_progress_bar)
  private val retry: LinearLayout by bindView(R.id.main_retry)

  private lateinit var launchesListAdapter: LaunchesListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    toolbar.setOnMenuItemClickListener(this)
    toolbar.inflateMenu(R.menu.menu_filter)

    inject(this)

    retry.setOnClickListener {
      presenter.getSpaceXInformation()
    }

    launchesListAdapter = launchesList.adapter as? LaunchesListAdapter
      ?: LaunchesListAdapter(Glide.with(this)) { presenter.onClickLaunchItem(it) }

    launchesList.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = launchesListAdapter
    }

    lifecycle.addObserver(presenter)
  }

  override fun render(state: MainViewState) {
    when (state) {
      is MainViewState.Error -> {
        progressBar.visibility = View.GONE
        infoContainer.visibility = View.GONE
        retry.visibility = View.VISIBLE
      }

      is MainViewState.Loading -> {
        progressBar.visibility = View.VISIBLE
        infoContainer.visibility = View.GONE
        retry.visibility = View.GONE
      }
      is MainViewState.ShowInformation -> {
        progressBar.visibility = View.GONE
        retry.visibility = View.GONE

        companyInfo.text = formattedCompanyInfoText(state.information.companyInformation)

        launchesListAdapter.launches = state.information.launches

        infoContainer.visibility = View.VISIBLE
      }

      is MainViewState.ShowLinksDialog -> {
        if (!state.links.wikipedia.isNullOrEmpty() && !state.links.article.isNullOrEmpty() && !state.links.video.isNullOrEmpty()) {
          val linksDialog = LinksDialog.newInstance(state.links)
          linksDialog.show(supportFragmentManager, "LinksDialog")
        }
      }

      is MainViewState.ShowFilterDialog -> {
        val filterDialog = FilterDialog.newInstance(state.filter)
        filterDialog.show(supportFragmentManager, "FilterDialog")
      }

      is MainViewState.ShowLaunches -> {
        progressBar.visibility = View.GONE
        retry.visibility = View.GONE

        launchesListAdapter.launches = state.launches

        infoContainer.visibility = View.VISIBLE
      }
    }
  }

  override fun onMenuItemClick(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.filter -> {
        presenter.onClickFilter()
        return true
      }
    }
    return false
  }

  override fun onFilterClicked(filter: LaunchesFilter) {
    presenter.onFilterLaunches(filter)
  }

  private fun formattedCompanyInfoText(companyInfo: CompanyInfo): String {
    return getString(
      R.string.company_info,
      companyInfo.companyName,
      companyInfo.founder,
      companyInfo.foundedYear,
      companyInfo.numEmployers,
      companyInfo.launchSite,
      companyInfo.valuation
    )
  }

  @Subcomponent
  interface Component {
    @Subcomponent.Factory
    interface Factory {
      fun create(@BindsInstance view: MainView): Component
    }

    fun inject(activity: MainActivity)
  }
}
