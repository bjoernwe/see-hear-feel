package dev.upaya.shf.ui.session

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import dev.upaya.shf.app.SHFActivity


@Composable
internal fun KeepScreenOn() {
    val context = LocalContext.current
    DisposableEffect(context) {
        context.getActivity()?.setKeepScreenOn()
        onDispose {
            context.getActivity()?.clearKeepScreenOn()
        }
    }
}


private fun Context.getActivity(): SHFActivity? = when (this) {
    is SHFActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}


private fun Activity.setKeepScreenOn() {
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}


private fun Activity.clearKeepScreenOn() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}
