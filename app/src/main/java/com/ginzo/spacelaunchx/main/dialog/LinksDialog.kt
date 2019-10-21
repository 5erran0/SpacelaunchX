package com.ginzo.spacelaunchx.main.dialog

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ginzo.spacelaunchx.R
import com.ginzo.spacelaunchx.main.dtos.LinksDTO
import com.ginzo.spacex_info_domain.entities.Links

class LinksDialog : DialogFragment() {


  companion object {
    private const val ARG_LINKS = "links"

    fun newInstance(links: Links): LinksDialog {
      val args = Bundle()
      args.putParcelable(ARG_LINKS, LinksDTO(links))

      val fragment = LinksDialog()
      fragment.arguments = args

      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_links, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val article = view.findViewById<TextView>(R.id.article)
    val wikipedia = view.findViewById<TextView>(R.id.wikipedia)
    val video = view.findViewById<TextView>(R.id.video)


    val links = arguments!!.getParcelable<LinksDTO>(ARG_LINKS)!!.toDomain()

    if (!links.article.isNullOrEmpty()) {
      article.visibility = View.VISIBLE
      article.setOnClickListener {
        openUrl(links.article!!)
      }
    } else {
      article.visibility = View.GONE
    }

    if (!links.wikipedia.isNullOrEmpty()) {
      wikipedia.visibility = View.VISIBLE
      wikipedia.setOnClickListener {
        openUrl(links.wikipedia!!)
      }
    } else {
      wikipedia.visibility = View.GONE
    }

    if (!links.video.isNullOrEmpty()) {
      video.visibility = View.VISIBLE
      video.setOnClickListener {
        openUrl(links.video!!)
      }
    } else {
      video.visibility = View.GONE
    }
  }

  private fun openUrl(url: String) {
    startActivity(Intent(ACTION_VIEW, Uri.parse(url)))
  }
}