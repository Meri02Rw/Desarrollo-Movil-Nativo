package com.example.miniproyecto01.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Carrera(carrera: String, onCarreraChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = carrera,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxSize(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Ingeniería de Software") },
                onClick = {
                    onCarreraChange("Ingeniería de Software")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Ingeniería Civil") },
                onClick = {
                    onCarreraChange("Ingeniería Civil")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Médico General") },
                onClick = {
                    onCarreraChange("Médico General")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Ciencias de la Educación") },
                onClick = {
                    onCarreraChange("Ciencias de la Educación")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Derecho") },
                onClick = {
                    onCarreraChange("Derecho")
                    expanded = false
                }
            )
        }
    }
}