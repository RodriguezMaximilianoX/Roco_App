package com.rmxdev.rocoapp.presenter.resetpassword

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val auth: FirebaseAuth
): ViewModel() {

    private val _resetPasswordState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Idle)
    var resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState

    fun resetPassword(email: String) {
        if (email.isNotBlank()) {
            _resetPasswordState.value = ResetPasswordState.Loading
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    _resetPasswordState.value = if (task.isSuccessful) {
                        ResetPasswordState.Success
                    } else {
                        ResetPasswordState.Error(task.exception?.message ?: "Password reset failed")
                    }
                }

        }
        else{
            _resetPasswordState.value = ResetPasswordState.Error("Email is empty")
        }
    }

    fun resetPasswordSuccess() {
        _resetPasswordState.value = ResetPasswordState.Idle

    }
}