package com.example.miniproyecto01.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NombreCompleto(nombreCompleto: String, onNombreCompletoChange: (String) -> Unit) {

    OutlinedTextField(
        value = nombreCompleto,
        onValueChange = onNombreCompletoChange,
        label = { Text("Escribe tu texto") },
        modifier = Modifier.fillMaxWidth()
    )
}