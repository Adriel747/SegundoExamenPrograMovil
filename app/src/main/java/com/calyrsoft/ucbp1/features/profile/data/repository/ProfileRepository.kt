package com.calyrsoft.ucbp1.features.profile.data.repository

import com.calyrsoft.ucbp1.features.profile.domain.model.ProfileModel
import com.calyrsoft.ucbp1.features.profile.domain.model.valueobjects.*
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                name = ProfileName("Homero J. Simpson"),
                email = ProfileEmail("homero.simpson@springfieldmail.com"),
                cellphone = ProfileCellphone("+1 (939) 555‑7422"),
                pathUrl = ProfilePathUrl("https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg"),
                summary = ProfileSummary("Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear.")
            )
        )
    }
}
