package dev.upaya.shf.ui.settings.composables


data class UserSettingParams(
    val emailAddress: String?,
    val onLogInClick: () -> Unit,
    val onLogOutClick: () -> Unit,
    val toggleLogInEnabled: Boolean,
)
