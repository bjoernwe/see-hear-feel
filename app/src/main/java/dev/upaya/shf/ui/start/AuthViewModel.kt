package dev.upaya.shf.ui.start

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.upaya.shf.data.auth.AuthRepository
import dev.upaya.shf.data.preferences.PreferencesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    preferencesRepository: PreferencesRepository,
) : ViewModel() {

    val isLogInEnabled = preferencesRepository.isLogInEnabled
    val logInStatus = authRepository.logInStatus
    val userEmail = authRepository.userEmail

    fun signIn(activityContext: Context) {
        viewModelScope.launch {
            authRepository.signIn(activityContext)
        }
    }

    fun signOut(activityContext: Context) {
        viewModelScope.launch {
            authRepository.signOut(activityContext)
        }
    }
}
