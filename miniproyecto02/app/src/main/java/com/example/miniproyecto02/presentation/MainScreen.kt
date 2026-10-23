package com.example.miniproyecto02.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.miniproyecto02.components.FormularioTarea
import com.example.miniproyecto02.components.TareaItem
import com.example.miniproyecto02.data.Tarea
import com.example.miniproyecto02.domain.AppDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val tareaDao = db.tareaDao()

    val tareas by tareaDao.obtenerTodas()
        .collectAsState(initial = emptyList())

    val coroutineScope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    var filtro by remember { mutableStateOf("Todas") }
    var mostrarFormulario by remember { mutableStateOf(false) }
    var tareaEditar by remember { mutableStateOf<Tarea?>(null) }

    val listaTareas = when (filtro) {
        "Pendientes" -> tareas.filter { it.estado == "Pendiente" }
        "Completadas" -> tareas.filter { it.estado == "Completada" }
        else -> tareas
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Administrador de Tareas") },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Menú"
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Todas") },
                            onClick = {
                                filtro = "Todas"
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Pendientes") },
                            onClick = {
                                filtro = "Pendientes"
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Completadas") },
                            onClick = {
                                filtro = "Completadas"
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Eliminar completadas") },
                            onClick = {
                                coroutineScope.launch {
                                    tareaDao.eliminarCompletadas()
                                }
                                expanded = false
                            }
                        )
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    tareaEditar = null
                    mostrarFormulario = true
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaTareas) { tarea ->

                TareaItem(
                    tarea = tarea,

                    onCompletar = { estado ->
                        coroutineScope.launch {
                            tareaDao.actualizar(
                                Tarea(
                                    id = tarea.id,
                                    titulo = tarea.titulo,
                                    descripcion = tarea.descripcion,
                                    estado = estado
                                )
                            )
                        }
                    },

                    onEditar = {
                        tareaEditar = tarea
                        mostrarFormulario = true
                    },

                    onEliminar = {
                        coroutineScope.launch {
                            tareaDao.eliminar(tarea)
                        }
                    }
                )
            }
        }
    }

    if (mostrarFormulario) {
        FormularioTarea(
            tarea = tareaEditar,

            onGuardar = { titulo, descripcion ->
                val tareaActual = tareaEditar

                coroutineScope.launch {
                    if (tareaActual == null) {

                        tareaDao.insertar(
                            Tarea(
                                titulo = titulo,
                                descripcion = descripcion,
                                estado = "Pendiente"
                            )
                        )

                    } else {

                        tareaDao.actualizar(
                            Tarea(
                                id = tareaActual.id,
                                titulo = titulo,
                                descripcion = descripcion,
                                estado = tareaActual.estado
                            )
                        )
                    }
                }

                mostrarFormulario = false
                tareaEditar = null
            },

            onCancelar = {
                mostrarFormulario = false
                tareaEditar = null
            }
        )
    }
}
