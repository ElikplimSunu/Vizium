package com.sunueric.prototype1.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.auth.AuthViewModel
import com.sunueric.prototype1.ui.utils.Screens
import com.sunueric.testingapicall2.data.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: AuthViewModel?,
    navController: NavController
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            SignInContent(viewModel, innerPadding, snackbarHostState, navController)
        }
    )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(viewModel: AuthViewModel?,
                  innerPadding: PaddingValues,
                  snackbarHostState: SnackbarHostState,
                  navController: NavController
) {
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val loginFlow = viewModel?.loginFlow?.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(29.dp)){
        Text(
            text = "Hello! Welcome back!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = "Hello again, You’ve been missed!",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFA8A2A2),
            )
        )

        Spacer(modifier = Modifier.height(39.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            label = { Text(text = "Email Address") },
            placeholder = { Text(text = "johndoe@example.com") },
            value = email.value,
            onValueChange = { email.value = it },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(38.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "password") },
            value = password.value,
            onValueChange = { password.value = it },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painterResource(id = if (passwordVisibility) R.drawable.visibility_off else R.drawable.visible),
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(38.dp))

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            CustomCheckbox("Remember me")


            Text(
                text = "Forgot Password?",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Blue
                ),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .clickable { /*TODO*/ }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(6.dp),
            onClick = {
                viewModel?.login(email.value.text, password.value.text)
            }) {
            when (loginFlow?.value) {
                is Resource.Loading -> {
                    // Display a smaller CircularProgressIndicator when loading
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                }

                else -> {
                    // Otherwise, display the "Sign Up" text
                    Text("Sign In")
                }
            }
        }
        Spacer(modifier = Modifier.height(34.dp))

        TextDivider(label = "Or Login with")

        Spacer(modifier = Modifier.height(34.dp))


        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            GoogleButton(Modifier.weight(1f), onClick = { /*TODO*/ })

            Spacer(modifier = Modifier.width(40.dp))

            FacebookButton(Modifier.weight(1f), onClick = { /*TODO*/ })
        }

        Spacer(modifier = Modifier.height(70.dp))

        CustomClickableText(text = "Don’t have an account?", clickableText = "Sign Up", onClick = {
            navController.navigate(Screens.SignUp.route){
                popUpTo(Screens.SignIn.route) { inclusive = true }
            }
        })

        loginFlow?.value?.let {
            when(it) {
                is Resource.Loading -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Loading..."
                        )
                    }
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        snackbarHostState.showSnackbar(
                            message = "Login successful"
                        )
                        navController.navigate(Screens.Grades.route) {
                            popUpTo(Screens.SignIn.route) { inclusive = true }
                        }
                    }
                }
                is Resource.Error -> {
                    LaunchedEffect(it.exception.message) {
                        snackbarHostState.showSnackbar(
                            message = it.exception.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpScreenPreviewDark() {
    SignInScreen(null, navController = NavController(LocalContext.current))
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SignUpScreenPreviewLight() {
    SignInScreen(null, navController = NavController(LocalContext.current))
}