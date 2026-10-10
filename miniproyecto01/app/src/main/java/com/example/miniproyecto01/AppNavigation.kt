package com.example.miniproyecto01

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "registro_screen"
    ) {

        // Pantalla 1
        composable("registro_screen") {
            RegistroScreen(
                onNavigateToDetalleScreen = {
                        matricula, nombreCompleto, carrera, turno, estatus ->

                    navController.navigate(
                        "detalle_screen/" +
                                "${Uri.encode(matricula)}/" +
                                "${Uri.encode(nombreCompleto)}/" +
                                "${Uri.encode(carrera)}/" +
                                "${Uri.encode(turno)}/" +
                                "$estatus"
                    )
                }
            )
        }

        // Pantalla 2
        composable(
            route = "detalle_screen/{matricula}/{nombreCompleto}/{carrera}/{turno}/{estatus}",
            arguments = listOf(
                navArgument("matricula") {
                    type = NavType.StringType
                },
                navArgument("nombreCompleto") {
                    type = NavType.StringType
                },
                navArgument("carrera") {
                    type = NavType.StringType
                },
                navArgument("turno") {
                    type = NavType.StringType
                },
                navArgument("estatus") {
                    type = NavType.BoolType
                }
            )
        ) { backStackEntry ->

            val matricula = backStackEntry.arguments?.getString("matricula") ?: ""
            val nombreCompleto = backStackEntry.arguments?.getString("nombreCompleto") ?: ""
            val carrera = backStackEntry.arguments?.getString("carrera") ?: ""
            val turno = backStackEntry.arguments?.getString("turno") ?: ""
            val estatus = backStackEntry.arguments?.getBoolean("estatus") ?: false

            DetalleScreen(
                matricula = matricula,
                nombreCompleto = nombreCompleto,
                carrera = carrera,
                turno = turno,
                estatus = estatus,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}