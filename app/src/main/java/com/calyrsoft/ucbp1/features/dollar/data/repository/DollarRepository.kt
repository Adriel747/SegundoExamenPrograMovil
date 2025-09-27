package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DollarRepository(val realTimeRemoteDataSource: RealTimeRemoteDataSource): IDollarRepository {
    override suspend fun getDollar(): Flow<DollarModel>{
    //  return flow{
    //    emit (DollarModel"6.96","12.6")
    //}
    //}
    return realTimeRemoteDataSource.getDollarUpdates()
}
}
