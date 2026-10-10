package com.example.miniproyecto01.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Turno(turno: String, onTurnoChange: (String) -> Unit) {
    

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (turno == "Matutino"),
            onClick = { onTurnoChange ("Matutino") }
        )
        Text(
            text = "Matutino",
            modifier = Modifier.clickable { onTurnoChange("Matutino") }
        )

        Spacer(modifier = Modifier.width(16.dp))

        RadioButton (
            selected = (turno == "Vespertino"),
            onClick = { onTurnoChange("Vespertino") }

        )
        Text(
            text = "Vespertino",
            modifier = Modifier.clickable { onTurnoChange("Vespertino") }
        )
    }
}