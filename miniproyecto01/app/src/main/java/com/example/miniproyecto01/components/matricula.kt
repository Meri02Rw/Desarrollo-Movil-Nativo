package com.example.miniproyecto01.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Matricula(matricula: String, onMatriculaChange: (String) -> Unit) {

    OutlinedTextField(
        value = matricula,
        onValueChange = onMatriculaChange,
        label = { Text("Escribe tu texto") },
        modifier = Modifier.fillMaxWidth()
    )
}