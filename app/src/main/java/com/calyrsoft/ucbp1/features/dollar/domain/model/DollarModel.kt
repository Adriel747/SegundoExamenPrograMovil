package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel (
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    var dollarParallelBuy: String? = null,
    var dollarParallelSell: String? = null,
    val timestamp: Long = 0L

){
}