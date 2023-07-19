package com.sunueric.prototype1.ui.composables

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.sunueric.prototype1.R

/** This function is used to create a list item.
 *  @param context is the context of the activity.
 *  @param textToSpeech is the text to speech object.
 *  @param chunk is the text to be spoken.
 *  @param activity is the activity to be started.
 **/
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ListItem(
    context: Context,
    textToSpeech: TextToSpeech,
    chunk: String,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    activity: Activity
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = paddingTop.dp, bottom = paddingBottom.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                "One Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    onDoubleClick = {
                        Toast
                            .makeText(
                                context,
                                "Double Tap",
                                Toast.LENGTH_SHORT
                            )
                            .show()

                        context.startActivity(
                            Intent(
                                context,
                                activity::class.java
                            )
                        )
                    },
                    onLongClick = {
                        Toast
                            .makeText(context, "Long Tap", Toast.LENGTH_SHORT)
                            .show()
                        ContextCompat.getSystemService(
                            context,
                            Vibrator::class.java
                        )?.vibrate(100)

                        textToSpeech.speak(
                            chunk,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            ""
                        )

                    }
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(5f), text = chunk, style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B2559)
                ).copy(lineHeight = 23.sp)
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play course"
            )
        }

    }
}