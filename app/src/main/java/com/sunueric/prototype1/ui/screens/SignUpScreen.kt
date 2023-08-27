package com.sunueric.prototype1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.auth.AuthViewModel
import com.sunueric.prototype1.ui.utils.Screens
import com.sunueric.testingapicall2.data.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: AuthViewModel?,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            SignUpContent(viewModel, innerPadding, snackbarHostState, navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    viewModel: AuthViewModel?,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current.applicationContext
    val signUpFlow = viewModel?.signUpFlow?.collectAsState()
//    val state = viewModel.signUpState.collectAsState(initial = null)
    var checkedState by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Create Account ",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "Connect with your Friends Today!",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF959AA1),
                )
            )

            Spacer(modifier = Modifier.height(39.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                label = { Text(text = "Name") },
                placeholder = { Text(text = "John Doe") },
                value = name.value,
                onValueChange = { name.value = it },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

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

            Spacer(modifier = Modifier.height(20.dp))


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

            Spacer(modifier = Modifier.height(20.dp))

            CustomCheckbox("I agree to the Terms and Conditions")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    viewModel?.signUp(name.value.text, email.value.text, password.value.text)
                }) {
                when (signUpFlow?.value) {
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
                        Text("Sign Up")
                    }
                }
            }

            Spacer(modifier = Modifier.height(34.dp))

            TextDivider(label = "Or Login with")

            Spacer(modifier = Modifier.height(34.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                GoogleButton(Modifier.weight(1f), onClick = { /*TODO*/ })

                Spacer(modifier = Modifier.width(40.dp))

                FacebookButton(Modifier.weight(1f), onClick = { /*TODO*/ })
            }

            Spacer(modifier = Modifier.height(81.dp))

            CustomClickableText(
                text = "Already have an account? ",
                clickableText = "Log In",
                onClick = {
                    navController.navigate(Screens.SignIn.route) {
                        popUpTo(Screens.SignUp.route) { inclusive = true }
                    }
                }
            )
            signUpFlow?.value?.let {
                when (it) {
                    is Resource.Loading -> {
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = "Loading",
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }

                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = "Sign Up Successful"
                            )
                            navController.navigate(Screens.Grades.route) {
                                popUpTo(Screens.SignUp.route) { inclusive = true }
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
    }

@Composable
fun CustomCheckbox(text: String) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickable { checked = !checked },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text
        )
    }
}

@Composable
fun CustomClickableText(text: String, clickableText: String, onClick: () -> Unit) {

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFFA2A6AC))) {
            append(text)
        }

        pushStringAnnotation(
            tag = "click",
            annotation = clickableText
        )
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(clickableText)
        }
        pop()
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        ClickableText(
            text = annotatedString,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF188F79)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) { offset ->
            annotatedString.getStringAnnotations(tag = "click", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }
        }
    }
}

@Composable
fun GoogleButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
        .height(46.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google logo",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Google",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FacebookButton(modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(46.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fb_logo),
                contentDescription = "Facebook Icon",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Facebook",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TextDivider(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            color = Color.Gray
        )
        Text(
            text = label,
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            )
        )
        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(null, NavController(LocalContext.current))
}