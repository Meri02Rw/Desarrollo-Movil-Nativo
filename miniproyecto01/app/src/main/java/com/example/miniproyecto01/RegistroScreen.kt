package com.example.miniproyecto01

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miniproyecto01.components.Carrera
import com.example.miniproyecto01.components.Estatus
import com.example.miniproyecto01.components.Matricula
import com.example.miniproyecto01.components.NombreCompleto
import com.example.miniproyecto01.components.Turno
import com.example.miniproyecto01.data.PreferencesManager

@Composable
fun RegistroScreen(onNavigateToDetalleScreen: (String, String, String, String, Boolean) -> Unit) {
    var matricula by remember { mutableStateOf("") }
    var nombreCompleto by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    var turno by remember { mutableStateOf("Matutino") }
    var estatus by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    // Carga los datos guardados al abrir la pantalla
    LaunchedEffect(Unit) {
        matricula = preferencesManager.getMatricula()
        nombreCompleto = preferencesManager.getNombreCompleto()
        carrera = preferencesManager.getCarrera()
        turno = preferencesManager.getTurno()
        estatus = preferencesManager.getEstatus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Miniproyecto 01: Registro de Estudiantes",
            fontSize = 22.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        // Matrícula
        Text("Matrícula", style = MaterialTheme.typography.titleMedium)
        Matricula(
            matricula  = matricula,
            onMatriculaChange  = { matricula = it }
        )

        HorizontalDivider()

        // Nombre completo
        Text("Nombre completo", style = MaterialTheme.typography.titleMedium)
        NombreCompleto(
            nombreCompleto  = nombreCompleto,
            onNombreCompletoChange  = { nombreCompleto = it }
        )

        HorizontalDivider()

        // Carrera
        Text("Carrera", style = MaterialTheme.typography.titleMedium)
        Carrera(
            carrera  = carrera,
            onCarreraChange  = { carrera = it }
        )

        HorizontalDivider()

        // Turno
        Text("Turno", style = MaterialTheme.typography.titleMedium)
        Turno(
            turno  = turno,
            onTurnoChange  = { turno = it }
        )

        HorizontalDivider()

        // Estatus
        Text("Estatus", style = MaterialTheme.typography.titleMedium)
        Estatus(
            estatus  = estatus,
            onEstatusChange  = { estatus = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (matricula.isNotBlank() &&
                    nombreCompleto.isNotBlank() &&
                    carrera.isNotBlank()
                ) {
                    preferencesManager.saveSettings(
                        matricula = matricula,
                        nombreCompleto = nombreCompleto,
                        carrera = carrera,
                        turno = turno,
                        estatus = estatus
                    )
                    onNavigateToDetalleScreen(
                        matricula,
                        nombreCompleto,
                        carrera,
                        turno,
                        estatus
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Completa todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar estudiante")
        }

        // Botón: Recargar/Recuperar
        OutlinedButton(
            onClick = {
                matricula = preferencesManager.getMatricula()
                nombreCompleto = preferencesManager.getNombreCompleto()
                carrera = preferencesManager.getCarrera()
                turno = preferencesManager.getTurno()
                estatus = preferencesManager.getEstatus()
                Toast.makeText(
                    context,
                    "Datos cargados",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recargar Datos Guardados")
        }
    }
}
