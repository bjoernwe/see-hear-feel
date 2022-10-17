package dev.upaya.shf.ui

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.upaya.shf.SHFActivity


@Composable
internal fun SetStatusBarColor(color: Color = MaterialTheme.colors.background) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = color)
}


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
