package com.rmxdev.rocoapp.presenter.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    var loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String){
       _loginState.value = LoginState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                _loginState.value = if(task.isSuccessful){
                    LoginState.Success
                }else{
                    LoginState.Error(task.exception?.message ?: "Login failed")
                }
            }
    }
}
