package com.ginzo.spacelaunchx.main.dtos

import com.ginzo.spacex_info_domain.entities.CompanyInfo
import com.ginzo.spacex_info_domain.entities.Launch

data class SpaceXInformationDTO(
  val companyInformation: CompanyInfo,
  val launches: List<Launch>
)