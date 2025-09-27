package com.calyrsoft.ucbp1.features.profile.domain.model.valueobjects


class ProfileName(val value: String) {
    init { require(value.isNotBlank()) { "El nombre no puede estar vacío" } }
}

class ProfileEmail(val value: String) {
    init { require(value.contains("@")) { "Email inválido" } }
}

class ProfileCellphone(val value: String) {
    init { require(value.isNotBlank()) { "Celular no puede estar vacío" } }
}

class ProfilePathUrl(val value: String) {
    init { require(value.isNotBlank()) { "URL no puede estar vacía" } }
}

class ProfileSummary(val value: String) {
    init { require(value.isNotBlank()) { "Resumen no puede estar vacío" } }
}