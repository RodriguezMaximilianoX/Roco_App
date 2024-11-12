package com.rmxdev.rocoapp.presenter.initial

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    val isUserLoggedIn: Boolean
        get() = auth.currentUser != null
}