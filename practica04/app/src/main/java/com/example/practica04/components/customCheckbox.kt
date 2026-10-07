package com.example.practica04.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun CustomCheckbox() {
    var isCheck by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isCheck,
            onCheckedChange = { isCheck = it }
        )
        Text("Acepto los términos y condicones")
    }
}