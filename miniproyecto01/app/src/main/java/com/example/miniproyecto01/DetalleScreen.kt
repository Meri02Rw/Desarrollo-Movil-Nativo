package com.example.miniproyecto01

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    matricula: String,
    nombreCompleto: String,
    carrera: String,
    turno: String,
    estatus: Boolean,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Confirmación de registro")
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFF3E5F5)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Datos del estudiante:",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Matrícula: $matricula",
                fontSize = 18.sp
            )

            Text(
                text = "Nombre completo: $nombreCompleto",
                fontSize = 18.sp
            )

            Text(
                text = "Carrera: $carrera",
                fontSize = 18.sp
            )

            Text(
                text = "Turno: $turno",
                fontSize = 18.sp
            )

            Text(
                text = "Estatus: ${if (estatus) "Activo" else "Inactivo"}",
                fontSize = 18.sp
            )
        }
    }
}