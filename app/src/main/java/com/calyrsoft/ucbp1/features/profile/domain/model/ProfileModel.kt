package com.calyrsoft.ucbp1.features.profile.domain.model
import com.calyrsoft.ucbp1.features.profile.domain.model.valueobjects.*

data class ProfileModel(
    val pathUrl: ProfilePathUrl,
    val name: ProfileName,
    val email: ProfileEmail,
    val cellphone: ProfileCellphone,
    val summary: ProfileSummary
)
