package com.ginzo.spacelaunchx.main.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.ginzo.spacelaunchx.R
import com.ginzo.spacelaunchx.main.dtos.FilterDTO
import com.ginzo.spacex_info_domain.entities.LaunchesFilter
import com.ginzo.spacex_info_domain.entities.LaunchesFilter.Companion.ASC
import com.ginzo.spacex_info_domain.entities.LaunchesFilter.Companion.DESC
import org.threeten.bp.Year


class FilterDialog : DialogFragment() {
  private lateinit var newFilter: LaunchesFilter
  private lateinit var listener: FilterDialogListener

  interface FilterDialogListener {
    fun onFilterClicked(filter: LaunchesFilter)
  }

  companion object {
    private const val ARG_FILTER = "filter"

    fun newInstance(filter: LaunchesFilter): FilterDialog {
      val fragment = FilterDialog()

      val args = Bundle()
      args.putParcelable(ARG_FILTER, FilterDTO(filter))
      fragment.arguments = args

      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_filter, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val success = view.findViewById<CheckBox>(R.id.filter_launch_success)
    val year = view.findViewById<Spinner>(R.id.filter_launch_year)
    val order = view.findViewById<Spinner>(R.id.filter_order)
    val filter = view.findViewById<TextView>(R.id.filter)
    val clear = view.findViewById<TextView>(R.id.clear)

    newFilter = arguments!!.getParcelable<FilterDTO>(ARG_FILTER)!!.toDomain()

    initSuccessFilter(success, newFilter.isSuccess)
    initYearFilter(year, newFilter.year)
    initOrderFilter(order, newFilter.order)

    filter.setOnClickListener {
      listener.onFilterClicked(newFilter)
      dismiss()
    }

    clear.setOnClickListener {
      listener.onFilterClicked(LaunchesFilter(null, null, null))
      dismiss()
    }
  }

  private fun initSuccessFilter(checkBox: CheckBox, isSuccess: Boolean?) {
    if (isSuccess != null) {
      checkBox.isChecked = newFilter.isSuccess!!
    }

    checkBox.setOnCheckedChangeListener { _, isChecked -> newFilter = newFilter.copy(isSuccess = isChecked) }
  }

  private fun initYearFilter(spinner: Spinner, currentFilter: Int?) {
    val yearsToFilter = (2000..(Year.now().value + 5)).toList()
    val yearsAdapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, yearsToFilter.map { it.toString() })

    spinner.adapter = yearsAdapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {}

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        newFilter = newFilter.copy(year = yearsToFilter[position])
      }
    }

    if (currentFilter != null) {
      spinner.setSelection(yearsToFilter.indexOf(newFilter.year!!))
    }
  }

  private fun initOrderFilter(spinner: Spinner, currentOrder: String?) {
    val orderBy = listOf(ASC, DESC)
    val orderByAdapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, orderBy)

    spinner.adapter = orderByAdapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {}

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        newFilter = newFilter.copy(order = orderBy[position])
      }
    }

    if (!currentOrder.isNullOrEmpty()) {
      spinner.setSelection(orderBy.indexOf(currentOrder))
    }
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)

    if (context is FilterDialogListener) {
      listener = context
    }
  }
}