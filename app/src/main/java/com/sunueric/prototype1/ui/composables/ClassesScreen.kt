package com.sunueric.prototype1.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunueric.prototype1.ui.theme.dmSans

@Composable
fun ClassesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)) {
            Text(
                text = "Select The Class You Want to Study",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 34.sp,
                    fontFamily = dmSans,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(131.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9D9B9B),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .background(color = Color(0xFFFDFDFD), shape = RoundedCornerShape(size = 6.dp)),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFDFDFD),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Class 1",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9D9B9B),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .background(color = Color(0xFFFDFDFD), shape = RoundedCornerShape(size = 6.dp)),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFDFDFD),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Class 1",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9D9B9B),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .background(color = Color(0xFFFDFDFD), shape = RoundedCornerShape(size = 6.dp)),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFDFDFD),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Class 1",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9D9B9B),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .background(color = Color(0xFFFDFDFD), shape = RoundedCornerShape(size = 6.dp)),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFDFDFD),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Class 1",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontFamily = dmSans,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF181818),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(size = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Previous",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontFamily = dmSans,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF181818),
                            textAlign = TextAlign.Center,
                        )
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .weight(1f),
                        shape = RoundedCornerShape(size = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Next",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            fontFamily = dmSans,
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

@Preview (showBackground = true)
@Composable
fun ClassesScreenPreview() {
    ClassesScreen()
}