package com.example.miniproyecto02.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.miniproyecto02.data.Tarea

@Composable
fun FormularioTarea(
    tarea: Tarea?,
    onGuardar: (String, String) -> Unit,
    onCancelar: () -> Unit
) {
    var titulo by remember(tarea) {
        mutableStateOf(tarea?.titulo ?: "")
    }

    var descripcion by remember(tarea) {
        mutableStateOf(tarea?.descripcion ?: "")
    }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text(if (tarea == null) "Nueva tarea" else "Editar tarea")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (titulo.isNotBlank()) {
                        onGuardar(titulo.trim(), descripcion.trim())
                    }
                },
                enabled = titulo.isNotBlank()
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}