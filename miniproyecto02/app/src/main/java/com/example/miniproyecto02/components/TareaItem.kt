package com.example.miniproyecto02.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miniproyecto02.data.Tarea

@Composable
fun TareaItem(
    tarea: Tarea,
    onCompletar: (String) -> Unit,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = tarea.estado == "Completada",
                onCheckedChange = {
                    if (it) {
                        onCompletar("Completada")
                    } else {
                        onCompletar("Pendiente")
                    }
                }
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(tarea.titulo)
                Text(tarea.descripcion)
                Text(tarea.estado)
            }

            IconButton(onClick = onEditar) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Editar"
                )
            }

            IconButton(onClick = onEliminar) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}
