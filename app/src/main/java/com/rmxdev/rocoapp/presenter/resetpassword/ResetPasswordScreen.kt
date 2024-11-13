package com.rmxdev.rocoapp.presenter.resetpassword

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ResetPasswordViewModel = hiltViewModel(),
    onResetPasswordSuccess: () -> Unit
) {

    var email: String by rememberSaveable { mutableStateOf("") }
    val resetPasswordState by viewModel.resetPasswordState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(vertical = 64.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo App",
            modifier = Modifier.align(Alignment.CenterHorizontally).size(250.dp)
        )
        Text(
            text = "Coloca un mail para generar una nueva contraseña",
            color = TextColor,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Ingresa un mail") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            maxLines = 1
        )
        Button(
            onClick = { viewModel.resetPassword(email) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = email.isNotBlank(),
            colors = buttonColors(ButtonColor, disabledContainerColor = DisabledButtonColor),
        ) {
            Text(text = "Restablecer contraseña", color = Color.White)
        }

        when (resetPasswordState) {
            is ResetPasswordState.Loading -> {
                CircularProgressIndicator()
            }
            is ResetPasswordState.Success -> {
                onResetPasswordSuccess()
                Toast.makeText(context, "Se envió un correo al mail indicado", Toast.LENGTH_SHORT).show()
                viewModel.resetPasswordSuccess()
            }
            is ResetPasswordState.Error -> {
                Toast.makeText(context, "El mail ingresado no es válido", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}
