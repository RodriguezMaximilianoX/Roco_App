package com.rmxdev.rocoapp.presenter.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmxdev.rocoapp.ui.theme.Background
import com.rmxdev.rocoapp.ui.theme.ButtonColor
import com.rmxdev.rocoapp.ui.theme.TextColor
import com.rmxdev.rocoapp.ui.theme.fontFamily

@Composable
fun InitialScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Roco App",
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp),
            color = TextColor,
        )
        Spacer(modifier = Modifier.weight(2f))
        Button(
            onClick = { navigateToLogin() },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .width(300.dp)
                .height(50.dp),
            colors = buttonColors(containerColor = ButtonColor, contentColor = Color.Black)
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        }
        Button(
            onClick = { navigateToSignUp() },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .width(300.dp)
                .height(50.dp),
            colors = buttonColors(containerColor = ButtonColor, contentColor = Color.Black)
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun InitialScreenPreview() {
    InitialScreen(navigateToLogin = {}, navigateToSignUp = {})

}