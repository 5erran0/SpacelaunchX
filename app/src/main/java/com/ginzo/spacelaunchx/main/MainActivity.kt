package com.ginzo.spacelaunchx.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ginzo.spacelaunchx.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun render(state: MainViewState) {

  }
}
