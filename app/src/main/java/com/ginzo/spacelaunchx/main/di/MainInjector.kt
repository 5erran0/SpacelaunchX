package com.ginzo.spacelaunchx.main.di

import com.ginzo.spacelaunchx.main.MainActivity

fun inject(activity: MainActivity) {
  (activity.applicationContext as MainComponentProvider).mainComponent
    .mainActivity()
    .create(activity)
    .inject(activity)
}