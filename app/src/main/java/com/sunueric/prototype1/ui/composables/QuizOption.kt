package com.sunueric.prototype1.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
//import com.sunueric.prototype1.QuizViewModel
import com.sunueric.prototype1.ui.theme.dmSans

@Composable
fun QuizOption (
    option: String,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
//    onClick: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .padding(top = paddingTop.dp, bottom = paddingBottom.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 40.dp, spotColor = Color(0x1F7090B0),
                ambientColor = Color(0x1F7090B0)
            )
            .height(80.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (text, radioButton) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(parent.start, margin = 20.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                text = option,
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF68769F),
                )
            )

            RadioButton(modifier = Modifier
                .constrainAs(radioButton) {
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }, selected = false,
//                onClick = onClick,
                onClick = { /*TODO*/ },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF1B2559),
                    unselectedColor = Color(0xFF68769F)
                )
            )
        }
    }
}