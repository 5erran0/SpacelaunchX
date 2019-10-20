package com.ginzo.spacelaunchx.main.adapter

import android.graphics.Color
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ginzo.commons.view.bindView
import com.ginzo.spacelaunchx.R
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.Links
import com.google.android.material.card.MaterialCardView
import kotlin.math.absoluteValue

class LaunchViewHolder(
  private val view: View,
  private val requestManager: RequestManager,
  private val onClickLaunchListener: (Links) -> Unit
) : RecyclerView.ViewHolder(view) {

  private val card: MaterialCardView by bindView(R.id.mcv_launch_container)
  private val patch: ImageView by bindView(R.id.iv_patch_image)
  private val missionName: TextView by bindView(R.id.tv_mission_name)
  private val launchDate: TextView by bindView(R.id.tv_launch_date)
  private val rocketInfo: TextView by bindView(R.id.tv_rocket_info)
  private val daysFromLaunch: TextView by bindView(R.id.tv_days_from_launch)
  private val success: TextView by bindView(R.id.tv_launch_success)

  fun bind(launch: Launch) {
    card.setOnClickListener { onClickLaunchListener(launch.links) }

    requestManager.load(launch.links.patch)
      .placeholder(R.drawable.ic_patch_placeholder24dp)
      .fitCenter()
      .into(patch)

    missionName.text = launch.missionName
    launchDate.text =
      view.context.getString(R.string.launch_date, launch.launchDate, launch.launchTime)

    rocketInfo.text =
      view.context.getString(R.string.rocket_info, launch.rocket.name, launch.rocket.type)

    daysFromLaunch.text = if (launch.daysFromLaunch > 0) {
      view.context.getString(R.string.days_from_launch, launch.daysFromLaunch.absoluteValue)
    } else {
      view.context.getString(R.string.days_since_launch, launch.daysFromLaunch.absoluteValue)
    }

    success.apply {
      if (launch.launchSuccess != null) {
        if (launch.launchSuccess!!) {
          setText(R.string.successfully)
          setTextColor(Color.GREEN)
        } else {
          setText(R.string.unsuccessfully)
          setTextColor(Color.RED)
        }
      } else {
        visibility = GONE
      }
    }
  }
}