package com.sunueric.prototype1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClassesScreen() {
    var currentPage by remember { mutableIntStateOf(1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 20.dp)
        ) {
            Text(
                text = "Select The Class You Want to Study",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(131.dp))

            if (currentPage == 1) {
                for (i in 1..3) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color(0xFF9D9B9B),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .background(
                                color = Color(0xFFFDFDFD),
                                shape = RoundedCornerShape(size = 8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFDFDFD),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Class $i",
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 28.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF181818),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                }
                Button(
                    onClick = { currentPage += 1 },
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Next",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF181818),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            } else {
                for (i in 4..6) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color(0xFF9D9B9B),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .background(
                                color = Color(0xFFFDFDFD),
                                shape = RoundedCornerShape(size = 8.dp)
                            ),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFDFDFD),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Class $i",
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 28.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF181818),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                }
                Button(
                    onClick = {currentPage -= 1},
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(size = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Previous",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF181818),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }

    }
}