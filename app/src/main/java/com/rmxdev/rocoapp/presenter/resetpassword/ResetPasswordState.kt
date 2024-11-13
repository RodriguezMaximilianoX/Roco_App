package com.rmxdev.rocoapp.presenter.resetpassword

sealed class ResetPasswordState {
    data object Idle : ResetPasswordState()
    data object Loading : ResetPasswordState()
    data object Success : ResetPasswordState()
    data class Error(val message: String) : ResetPasswordState()
}