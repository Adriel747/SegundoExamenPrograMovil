package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

// Modelo simple para los datos de la tarjeta.
private data class DollarField(val title: String, val value: String?)

// Función auxiliar para crear la lista de campos.
private fun createDollarFields(data: DollarModel): List<DollarField> {
    return listOf(
        DollarField("Oficial ", data.dollarOfficial),
        DollarField("Paralelo", data.dollarParallel),
        DollarField("Paralelo Compra", data.dollarParallelBuy),
        DollarField("Paralelo Venta", data.dollarParallelSell),
    )
}

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Cotizaciones del Dólar",
            modifier = Modifier.padding(bottom = 24.dp)
        )

        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))

            is DollarViewModel.DollarUIState.Success -> {
                // 1. Preparación de los datos
                val fields = createDollarFields(stateValue.data)

                // 2. Grid con 4 tarjetas
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(fields) { field ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(3.dp)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(text = field.title)
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "${field.value} Bs",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Texto de actualización
                Text(
                    text = "Última actualización: ${viewModelDollar.formatFecha(stateValue.data.timestamp)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}
