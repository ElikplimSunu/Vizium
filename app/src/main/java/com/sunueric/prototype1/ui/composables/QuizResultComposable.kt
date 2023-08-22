package com.sunueric.prototype1.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunueric.prototype1.ui.theme.Prototype1Theme
import com.sunueric.prototype1.ui.theme.dmSans

@Composable
fun QuizResultScreen() {
    val score = 5

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8FAFB))
            .padding(20.dp)
    ) {
        Text(
            text = "English Language",
            style = TextStyle(
                fontFamily = dmSans,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF68769F),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = when (score) {
                in 1..7 -> {
                    "You can do better"
                }

                in 8..10 -> {
                    "Congratulations!"
                }

                else -> {
                    "Something went wrong"
                }
            },
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = dmSans,
                fontWeight = FontWeight(800),
                color = Color(0xFF1B2559),
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x1F7090B0),
                    ambientColor = Color(0x1F7090B0)
                )
                .width(353.dp)
                .height(213.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your score",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF1B2559),
                        textAlign = TextAlign.Center,
                    )
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 64.sp,
                                fontFamily = dmSans,
                                fontWeight = FontWeight(800),
                                color = Color(0xFF1B2559),
                            )
                        ) {
                            append(score.toString())
                            append("/")
                        }
                        append("10")
                    },
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF68769F),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizResultScreenPreview() {
    Prototype1Theme {
        QuizResultScreen()
    }
}