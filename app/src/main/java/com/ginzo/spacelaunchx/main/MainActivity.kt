package com.ginzo.spacelaunchx.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginzo.commons.view.bindView
import com.ginzo.spacelaunchx.R
import com.ginzo.spacelaunchx.main.adapter.LaunchesListAdapter
import com.ginzo.spacelaunchx.main.di.inject
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter

  private val infoContainer: NestedScrollView by bindView(R.id.main_information_container)
  private val companyInfo: TextView by bindView(R.id.main_company_info)
  private val launchesList: RecyclerView by bindView(R.id.main_launches)
  private val progressBar: ProgressBar by bindView(R.id.main_progress_bar)
  private val retry: LinearLayout by bindView(R.id.main_retry)

  private lateinit var launchesListAdapter: LaunchesListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    inject(this)

    retry.setOnClickListener {
      presenter.getSpaceXInformation()
    }

    launchesListAdapter = launchesList.adapter as? LaunchesListAdapter
      ?: LaunchesListAdapter(Glide.with(this)) {}

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
    }
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
