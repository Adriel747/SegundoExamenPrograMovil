package com.calyrsoft.ucbp1.features.dollar.data.mapper

import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel() : DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarParallelBuy = dollarParallelBuy,
        dollarParallelSell = dollarParallelSell,
        timestamp = timestamp
    )
}

fun DollarModel.toEntity() : DollarEntity {
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel,
        dollarParallelBuy = dollarParallelBuy,
        dollarParallelSell = dollarParallelSell,
        timestamp = if (timestamp == 0L) System.currentTimeMillis() else timestamp
    )
}

