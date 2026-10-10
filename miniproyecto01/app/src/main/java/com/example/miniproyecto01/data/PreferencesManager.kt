package com.example.miniproyecto01.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    // Archivo XML interno donde Android guarda las preferencias
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    // Claves (keys)
    companion object {
        const val KEY_MATRICULA = "key_matricula"
        const val KEY_NOMBRECOMPLETO = "key_nombreCompleto"
        const val KEY_CARRERA = "key_carrera"
        const val KEY_TURNO = "key_turno"
        const val KEY_ESTATUS = "key_estatus"
    }

    // --- MÉTODOS DE GUARDADO (Escritura) ---
    fun saveSettings(
        matricula: String,
        nombreCompleto: String,
        carrera: String,
        turno: String,
        estatus: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_MATRICULA, matricula)
        editor.putString(KEY_NOMBRECOMPLETO, nombreCompleto)
        editor.putString(KEY_CARRERA, carrera)
        editor.putString(KEY_TURNO, turno)
        editor.putBoolean(KEY_ESTATUS, estatus)
        editor.apply()// Guarda de forma asincrona en disco
    }

    // --- MÉTODOS DE CONSULTA (Lectura) ---
    fun getMatricula(): String {
        return sharedPreferences.getString(KEY_MATRICULA, "") ?: ""
    }

    fun getNombreCompleto(): String {
        return sharedPreferences.getString(KEY_NOMBRECOMPLETO, "") ?: ""
    }

    fun getCarrera(): String {
        return sharedPreferences.getString(KEY_CARRERA, "") ?: ""
    }

    fun getTurno(): String {
        return sharedPreferences.getString(KEY_TURNO, "Matutino") ?: "Matutino"
    }

    fun getEstatus(): Boolean {
        return sharedPreferences.getBoolean(KEY_ESTATUS, false)
    }
}
