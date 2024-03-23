package dev.upaya.shf.data.user_interaction

import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi


class VibratorFactory {

    companion object {

        fun getVibrator(context: Context): Vibrator? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getVibratorNew(context)
            } else {
                getVibratorOld(context)
            }
        }

        @RequiresApi(Build.VERSION_CODES.S)
        private fun getVibratorNew(context: Context): Vibrator? {
            val vibratorManager: VibratorManager
            try {
                vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            } catch (e: NullPointerException) {
                return null
            }
            return vibratorManager.defaultVibrator
        }

        private fun getVibratorOld(context: Context): Vibrator? {
            val vibrator: Vibrator
            try {
                @Suppress("DEPRECATION")
                vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            } catch (e: NullPointerException) {
                return null
            }
            return vibrator
        }
    }
}
