package com.ginzo.spacelaunchx.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ginzo.spacelaunchx.R
import com.ginzo.spacex_info_domain.entities.Launch
import com.ginzo.spacex_info_domain.entities.Links

class LaunchesListAdapter(
  private val requestManager: RequestManager,
  private val onClickLaunchListener: (Links) -> Unit
) : RecyclerView.Adapter<LaunchViewHolder>() {

  var launches: List<Launch> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_launch, parent, false)
    return LaunchViewHolder(view, requestManager, onClickLaunchListener)
  }

  override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
    holder.bind(launches[position])
  }

  override fun getItemCount(): Int {
    return launches.size
  }
}
