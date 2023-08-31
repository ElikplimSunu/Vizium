package com.sunueric.prototype1.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.ui.theme.dmSans
import com.sunueric.prototype1.ui.utils.Screens

@Composable
fun FirstOnBoardingScreen(navController: NavController?) {
    Column(modifier = Modifier.fillMaxSize().padding(29.dp)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clip(shape = RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.onboarding_1), contentDescription = "")
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Empowering the visually impaired with knowledge at their fingertips. Dive into an inclusive educational experience tailored for you.",
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 30.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                Image(painter = painterResource(id = R.drawable.slider_1), contentDescription = "")

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000000),
                        contentColor = Color.White
                    ),
                    onClick = {
                        navController?.navigate(Screens.SecondOnBoadingScreen.route)
                    }) {
                    Text(text = "Get Started",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontFamily = dmSans,
                            fontWeight = FontWeight(400),
                            color = Color.White,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Skip",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .clickable {
                            navController?.navigate(Screens.Grades.route)
                        }
                )
            }
        }

    }
}

@Composable
fun SecondOnBoardingScreen(navController: NavController?) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clip(shape = RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.onboarding_2), contentDescription = "")
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().padding(29.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Our range of educational resources, from comprehensive texts to interactive quizzes, are crafted to cater to your unique learning style. Dive in, and let's learn together.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 30.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                Image(painter = painterResource(id = R.drawable.slider_2), contentDescription = "")

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000000),
                        contentColor = Color.White
                    ),
                    onClick = {
                        navController?.navigate(Screens.ThirdOnBoadingScreen.route)
                    }) {
                    Text(text = "Next",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontFamily = dmSans,
                            fontWeight = FontWeight(400),
                            color = Color.White,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Skip",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .clickable {
                            navController?.navigate(Screens.Grades.route)
                        }
                )
            }
        }

    }
}

@Composable
fun ThirdOnBoardingScreen(navController: NavController?) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clip(shape = RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.onboarding_2),
                contentDescription = "progress bar")
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)) {
            Column(modifier = Modifier.fillMaxSize().padding(29.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Enjoy a seamless learning experience with our integrated reader. Transforming text into clear audio, making content accessible and engaging.",
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 30.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                Image(painter = painterResource(id = R.drawable.slider_3), contentDescription = "")

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000000),
                        contentColor = Color.White
                    ),
                    onClick = {
                        navController?.navigate(Screens.Grades.route)
                    }) {
                    Text(text = "Let’s Make a Journey",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = dmSans,
                            fontWeight = FontWeight(500),
                            color = Color.White,
                        )
                    )
                }


            }
        }

    }
}

@Preview (showBackground = true)
@Composable
fun FirstOnBoardingScreenPreview() {
    FirstOnBoardingScreen(navController = null)
}

@Preview (showBackground = true)
@Composable
fun SecondOnBoardingScreenPreview() {
    SecondOnBoardingScreen(navController = null)
}

@Preview (showBackground = true)
@Composable
fun ThirdOnBoardingScreenPreview() {
    ThirdOnBoardingScreen(navController = null)
}