package dev.upaya.shf.background.notifications

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder


class BackgroundNotificationServiceConnection : ServiceConnection {

    var service: BackgroundNotificationService? = null
        private set

    override fun onServiceConnected(className: ComponentName, serviceBinder: IBinder) {
        val binder = serviceBinder as BackgroundNotificationService.LocalBinder
        service = binder.getService()
    }

    override fun onServiceDisconnected(arg0: ComponentName) {
        service = null
    }

    fun bindTo(context: Context) {
        Intent(context, BackgroundNotificationService::class.java).also { intent ->
            context.bindService(intent, this, Context.BIND_AUTO_CREATE)
        }
    }

    fun unbindFrom(context: Context) {
        context.unbindService(this)
    }
}