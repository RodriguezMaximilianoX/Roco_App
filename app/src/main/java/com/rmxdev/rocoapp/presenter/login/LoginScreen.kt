package com.rmxdev.rocoapp.presenter.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmxdev.rocoapp.R
import com.rmxdev.rocoapp.ui.theme.Background
import com.rmxdev.rocoapp.ui.theme.ButtonColor
import com.rmxdev.rocoapp.ui.theme.DisabledButtonColor
import com.rmxdev.rocoapp.ui.theme.SelectedField
import com.rmxdev.rocoapp.ui.theme.TextColor
import com.rmxdev.rocoapp.ui.theme.UnselectedField
import com.rmxdev.rocoapp.ui.theme.fontFamily

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    resetPassword: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar sesión",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor,
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo App",
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Ingresa tu mail", fontFamily = fontFamily, color = Color.White) },
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        TextField(value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    "Ingresa tu contraseña", fontFamily = fontFamily, color = Color.White
                )
            },
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                    val description =
                        if (passwordHidden) "Mostrar contraseña" else "Ocultar contraseña"
                    Icon(painterResource(id = visibilityIcon), contentDescription = description)
                }
            })
        TextButton(
            onClick = { resetPassword() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Olvide mi contraseña", color = TextColor)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.login(email, password) },
            enabled = email.isNotBlank() && password.isNotBlank(),
            colors = buttonColors(ButtonColor, disabledContainerColor = DisabledButtonColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 26.dp)
                .height(56.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        when (loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }

            is LoginState.Success -> {
                onLoginSuccess()
            }

            is LoginState.Error -> {
                LaunchedEffect(loginState) {
                    Toast.makeText(
                        context, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG
                    ).show()
                }
            }

            else -> {}
        }

    }
}
