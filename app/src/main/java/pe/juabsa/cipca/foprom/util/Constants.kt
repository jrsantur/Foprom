package pe.juabsa.cipca.foprom.util

import android.R

import com.google.android.gms.location.DetectedActivity


object Constants {
    const val BROADCAST_ACTION = "broadcast-action"
    const val ACTIVITY_KEY = "activites-key"
    const val ACTIVITY_RECOGNITION_INTERVAL: Long = 0
    const val UPDATE_INTERVAL: Long = 1000
    const val UPDATE_FASTEST_INTERVAL = UPDATE_INTERVAL / 2
    fun getStringActivity(type: Int): String {
        return when (type) {
            DetectedActivity.IN_VEHICLE -> "Vehículo"
            DetectedActivity.ON_BICYCLE -> "Bicicleta"
            DetectedActivity.ON_FOOT -> "Caminando o corriendo"
            DetectedActivity.RUNNING -> "Corriendo"
            DetectedActivity.STILL -> "Sin movimiento"
            DetectedActivity.TILTING -> "Inclinación brusca"
            DetectedActivity.UNKNOWN -> "Desconocido"
            DetectedActivity.WALKING -> "Caminando"
            else -> "Tipo no idenficado"
        }
    }
    /*
    fun getActivityIcon(type: Int): Int {
        return when (type) {
            //DetectedActivity.STILL -> R.drawable.ic_still
            //DetectedActivity.WALKING -> R.drawable.ic_walk
            //DetectedActivity.RUNNING -> R.drawable.ic_run
            //else -> R.drawable.ic_question
        }
    }
    */
}

