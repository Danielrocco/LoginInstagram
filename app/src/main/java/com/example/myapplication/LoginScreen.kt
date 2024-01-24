package com.example.myapplication

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        //Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("")}
    var passwd by rememberSaveable { mutableStateOf("")}

    Column(modifier = modifier) {
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(156.dp))
        Email(email) { email = it }
        Password(passwd) { passwd = it }
        //ForgotPass()
        //LoginButton()
        //LoginDivisor()
        //LoginSocial()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(passwd: String, function: (String) -> Unit) {
    var passVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = passwd,
        onValueChange = { function(it) },
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        placeholder = { Text(text = "Contraseña") },
        trailingIcon = {
            val image = if(passVisibility)
                Icons.Filled.VisibilityOff
            else
                Icons.Filled.Visibility
            IconButton(onClick = {passVisibility = !passVisibility}) {
                Icon(imageVector = image, contentDescription = "Show Password")
            }
        },
        visualTransformation = if(passVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, function: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { function(it) },
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        placeholder = { Text(text = "Email") }
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as? Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier
            .clickable { activity?.finish() })
}
