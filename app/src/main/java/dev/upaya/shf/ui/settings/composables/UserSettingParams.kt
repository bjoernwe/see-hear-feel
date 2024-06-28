package dev.upaya.shf.ui.settings.composables

import dev.upaya.shf.data.auth.LogInStatus


data class UserSettingParams(
    val logInStatus: LogInStatus,
    val emailAddress: String?,
    val onLogInClick: () -> Unit,
    val onLogOutClick: () -> Unit,
    val toggleLogInEnabled: Boolean,
)
