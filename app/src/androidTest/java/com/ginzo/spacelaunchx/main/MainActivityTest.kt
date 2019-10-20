package com.ginzo.spacelaunchx.main

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.ginzo.spacelaunchx.R
import com.ginzo.spacelaunchx.main.dtos.SpaceXInformationDTO
import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.Links
import com.ginzo.spacex_info_domain.entities.Rocket
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.absoluteValue

class MainActivityTest {
  private val context = ApplicationProvider.getApplicationContext<Context>()

  @Rule
  @JvmField
  val activityRule = ActivityTestRule(MainActivity::class.java)

  private val activity: MainActivity
    get() = activityRule.activity

  private lateinit var presenter: MainPresenter

  private fun runOnUiThread(runnable: () -> Unit) {
    activity.runOnUiThread(runnable)
  }

  @Before
  fun init() {
    activityRule.launchActivity(Intent(context, MainActivity::class.java))
    presenter = activity.presenter
  }

  @Test
  fun renderLoading() {
    runOnUiThread {
      activity.render(MainViewState.Loading)
    }

    onView(withId(R.id.main_progress_bar))
      .check(matches(isDisplayed()))
    onView(withId(R.id.main_information_container))
      .check(matches(not(isDisplayed())))
    onView(withId(R.id.main_retry))
      .check(matches(not(isDisplayed())))
  }

  @Test
  fun renderSuccess_From_Launch() {
    val information = SpaceXInformationDTO(
      CompanyInfo(
        "tesla",
        "elon musk",
        2000,
        3, 3,
        2000000
      ),
      listOf(
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
    )

    runOnUiThread {
      activity.render(MainViewState.ShowInformation(information))
    }

    onView(withId(R.id.main_progress_bar))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_information_container))
      .check(matches((isDisplayed())))

    onView(withId(R.id.main_retry))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_company_info))
      .check(matches(isDisplayed()))
      .check(
        matches(
          withText(
            context.getString(
              R.string.company_info,
              information.companyInformation.companyName,
              information.companyInformation.founder,
              information.companyInformation.foundedYear,
              information.companyInformation.numEmployers,
              information.companyInformation.launchSite,
              information.companyInformation.valuation
            )
          )
        )
      )

    onView(withId(R.id.tv_mission_name))
      .check(matches(withText(information.launches[0].missionName)))

    onView(withId(R.id.tv_launch_date))
      .check(
        matches(
          withText(
            context.getString(
              R.string.launch_date,
              information.launches[0].launchDate,
              information.launches[0].launchTime
            )
          )
        )
      )

    onView(withId(R.id.tv_rocket_info))
      .check(
        matches(
          withText(
            context.getString(
              R.string.rocket_info,
              information.launches[0].rocket.name,
              information.launches[0].rocket.type
            )
          )
        )
      )

    onView(withId(R.id.tv_days_from_launch))
      .check(
        matches(
          withText(
            context.getString(
              R.string.days_from_launch,
              information.launches[0].daysFromLaunch.absoluteValue
            )
          )
        )
      )

    onView(withId(R.id.tv_launch_success))
      .check(matches(withText(R.string.successfully)))
  }

  @Test
  fun renderUnsuccessfuly_From_Launch() {
    val information = SpaceXInformationDTO(
      CompanyInfo(
        "tesla",
        "elon musk",
        2000,
        3, 3,
        2000000
      ),
      listOf(
        Launch(
          flightNumber = "1",
          missionName = "2",
          launchDate = "aaaa/mm/dd",
          launchTime = "hh:mm",
          daysFromLaunch = 10,
          launchSuccess = false,
          rocket = Rocket("3", "4", "5"),
          links = Links("6", "7", "8", "9")
        )
      )
    )

    runOnUiThread {
      activity.render(MainViewState.ShowInformation(information))
    }

    onView(withId(R.id.main_progress_bar))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_information_container))
      .check(matches((isDisplayed())))

    onView(withId(R.id.main_retry))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_company_info))
      .check(matches(isDisplayed()))
      .check(
        matches(
          withText(
            context.getString(
              R.string.company_info,
              information.companyInformation.companyName,
              information.companyInformation.founder,
              information.companyInformation.foundedYear,
              information.companyInformation.numEmployers,
              information.companyInformation.launchSite,
              information.companyInformation.valuation
            )
          )
        )
      )

    onView(withId(R.id.tv_mission_name))
      .check(matches(withText(information.launches[0].missionName)))

    onView(withId(R.id.tv_launch_date))
      .check(
        matches(
          withText(
            context.getString(
              R.string.launch_date,
              information.launches[0].launchDate,
              information.launches[0].launchTime
            )
          )
        )
      )

    onView(withId(R.id.tv_rocket_info))
      .check(
        matches(
          withText(
            context.getString(
              R.string.rocket_info,
              information.launches[0].rocket.name,
              information.launches[0].rocket.type
            )
          )
        )
      )

    onView(withId(R.id.tv_days_from_launch))
      .check(
        matches(
          withText(
            context.getString(
              R.string.days_from_launch,
              information.launches[0].daysFromLaunch.absoluteValue
            )
          )
        )
      )

    onView(withId(R.id.tv_launch_success))
      .check(matches(withText(R.string.unsuccessfully)))
  }

  @Test
  fun render_Since_Launch() {
    val information = SpaceXInformationDTO(
      CompanyInfo(
        "tesla",
        "elon musk",
        2000,
        3, 3,
        2000000
      ),
      listOf(
        Launch(
          flightNumber = "1",
          missionName = "2",
          launchDate = "aaaa/mm/dd",
          launchTime = "hh:mm",
          daysFromLaunch = -10,
          launchSuccess = null,
          rocket = Rocket("3", "4", "5"),
          links = Links("6", "7", "8", "9")
        )
      )
    )

    runOnUiThread {
      activity.render(MainViewState.ShowInformation(information))
    }

    onView(withId(R.id.main_progress_bar))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_information_container))
      .check(matches((isDisplayed())))

    onView(withId(R.id.main_retry))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_company_info))
      .check(matches(isDisplayed()))
      .check(
        matches(
          withText(
            context.getString(
              R.string.company_info,
              information.companyInformation.companyName,
              information.companyInformation.founder,
              information.companyInformation.foundedYear,
              information.companyInformation.numEmployers,
              information.companyInformation.launchSite,
              information.companyInformation.valuation
            )
          )
        )
      )

    onView(withId(R.id.tv_mission_name))
      .check(matches(withText(information.launches[0].missionName)))

    onView(withId(R.id.tv_launch_date))
      .check(
        matches(
          withText(
            context.getString(
              R.string.launch_date,
              information.launches[0].launchDate,
              information.launches[0].launchTime
            )
          )
        )
      )

    onView(withId(R.id.tv_rocket_info))
      .check(
        matches(
          withText(
            context.getString(
              R.string.rocket_info,
              information.launches[0].rocket.name,
              information.launches[0].rocket.type
            )
          )
        )
      )

    onView(withId(R.id.tv_days_from_launch))
      .check(
        matches(
          withText(
            context.getString(
              R.string.days_since_launch,
              information.launches[0].daysFromLaunch.absoluteValue
            )
          )
        )
      )

    onView(withId(R.id.tv_launch_success))
      .check(matches(not(isDisplayed())))
  }

  @Test
  fun renderError() {
    runOnUiThread {
      activity.render(MainViewState.Error)
    }

    onView(withId(R.id.main_retry))
      .check(matches(isDisplayed()))

    onView(withId(R.id.main_progress_bar))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_information_container))
      .check(matches(not(isDisplayed())))

    onView(withId(R.id.main_retry))
      .perform(click())

    verify(presenter).getSpaceXInformation()
  }

}