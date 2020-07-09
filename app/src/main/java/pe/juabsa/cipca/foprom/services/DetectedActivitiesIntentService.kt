package pe.juabsa.cipca.foprom.services


import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity
import pe.juabsa.cipca.foprom.util.Constants


class DetectedActivitiesIntentService : IntentService(TAG) {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        // Lo primero es obtener el resultado de reconocimiento.
        val result = ActivityRecognitionResult.extractResult(intent)

        // Luego, obtén actividad más probable.
        val detectedActivity = result.mostProbableActivity
        Log.d(TAG, "Actividad dectectada: Tipo " +
                Constants.getStringActivity(detectedActivity.type).toString() +
                " - Confianza " + detectedActivity.confidence)
        var type = detectedActivity.type
        if (DetectedActivity.ON_FOOT == type) {
            type = walkingOrRunning(result.probableActivities)
        }


        // Ahora, crea un intent con una acción personalizada.
        val localIntent = Intent(Constants.BROADCAST_ACTION)

        // El siguiente paso, es poner las actividades en el intent
        localIntent.putExtra(Constants.ACTIVITY_KEY, type)

        // Y finalmente envía los datos hacia la actividad
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)
    }

    private fun walkingOrRunning(probableActivities: List<DetectedActivity>): Int {
        var walkActivity = 0
        var runningActivity = 0
        for (probableActivity in probableActivities) {
            if (probableActivity.type == DetectedActivity.WALKING) {
                walkActivity = probableActivity.confidence
            } else if (probableActivity.type == DetectedActivity.RUNNING) {
                runningActivity = probableActivity.confidence
            }
        }
        return if (walkActivity >= runningActivity) DetectedActivity.WALKING else DetectedActivity.RUNNING
    }

    companion object {
        private val TAG = DetectedActivitiesIntentService::class.java.simpleName
    }
}
