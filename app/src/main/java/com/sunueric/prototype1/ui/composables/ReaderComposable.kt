package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.R
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.data.Topic
import com.sunueric.prototype1.ui.theme.dmSans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.UUID

@Composable
fun ReaderScreen(navController: NavController, viewModel: SharedViewModel) {
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(false) }

    val textToSpeech = TextToSpeech(context, null)

    val selectedTopic = viewModel.selectedTopic.observeAsState()

    // Fetch the selected course and its topics
    val readerText = selectedTopic.value?.body
    val topicName = selectedTopic.value?.name
    val course = viewModel.selectedCourse.value?.name

    // Get the list of topics from the view model
    val topics = viewModel.topics.observeAsState()

    val mediaPlayer = remember { MediaPlayer() }

    // Track the current topic index
    var currentTopicIndex by rememberSaveable { mutableIntStateOf(0) }

    // Function to navigate to the next topic
    fun nextTopic() {
        // Stop and release the current media player
        if (isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            isPlaying = false
        }

        currentTopicIndex++
        if (currentTopicIndex >= (topics.value?.size ?: 0)) {
            currentTopicIndex = 0 // Wrap around to the first topic if reaching the end
        }
        viewModel.setSelectedTopic(topics.value?.get(currentTopicIndex) ?: Topic("", ""))
    }

    // Function to navigate to the previous topic
    fun previousTopic() {
        // Stop and release the current media player
        if (isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            isPlaying = false
        }
        currentTopicIndex--
        if (currentTopicIndex < 0) {
            currentTopicIndex = (topics.value?.size ?: 0) - 1 // Wrap around to the last topic if at the beginning
        }
        viewModel.setSelectedTopic(topics.value?.get(currentTopicIndex) ?: Topic("", ""))
    }

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        val (cardDesignLeft, cardDesignRight, textCard, playButton) = createRefs()
        Box(modifier = Modifier
            .constrainAs(cardDesignLeft) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 217.dp, bottom = 186.dp)
            .background(
                shape = RoundedCornerShape(bottomEnd = 20.dp),
                color = Color(0xFF595959)
            )
            .width(48.dp)
            .height(600.dp))

        Box(modifier = Modifier
            .constrainAs(cardDesignRight) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 217.dp, bottom = 186.dp)
            .background(
                shape = RoundedCornerShape(bottomStart = 20.dp),
                color = Color(0xFF595959)
            )
            .width(48.dp)
            .height(600.dp))

        Box(modifier = Modifier
            .constrainAs(textCard) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(top = 60.dp, bottom = 211.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(size = 6.dp))

            .background(shape = RoundedCornerShape(6.dp), color = Color.White)
            .padding(20.dp)
            .width(353.dp)
            .height(581.dp)) {

            DisplayingExtractedText(
                readerText = readerText!!,
                topicName = topicName!!,
                courseName = course!!
            )
        }

        Box(modifier = Modifier
            .constrainAs(playButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(bottom = 106.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        skipBehind(mediaPlayer)
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.reverse_5_secs),
                        contentDescription = "Skip 5 seconds behind",
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        previousTopic()
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.skip_previous),
                        contentDescription = "Previous topic",
                        tint = Color.Black
                    )
                }

                PauseAndPlayButton(
                    context,
                    isPlaying,
                    onTogglePlayPause = { isPlaying = it },
                    readerText!!,
                    mediaPlayer
                )

                IconButton(
                    onClick = {
                        nextTopic()
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.skip_next),
                        contentDescription = "Next topic",
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        skipAhead(mediaPlayer)
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.forward_5_secs),
                        contentDescription = "Skip 5 seconds ahead",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

// Displaying the extracted text from the pdf
@Composable
private fun DisplayingExtractedText(readerText: String, topicName: String, courseName: String) {
    Column {
        Text(
            text = topicName,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = dmSans,
                color = Color.Black
            ).copy(lineHeight = 32.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = courseName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = dmSans,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ).copy(lineHeight = 23.sp)
            )

            Text(
                text = readerText,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = dmSans,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                ).copy(lineHeight = 20.sp)
            )
        }
    }
}

// This is where the loading animation is displayed.
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RippleLoadingAnimation()
        }
    }
}

@Composable
fun PauseAndPlayButton(
    context: Context = LocalContext.current.applicationContext,
    isPlaying: Boolean,
    onTogglePlayPause: (Boolean) -> Unit,
    readerText: String,
    mediaPlayer: MediaPlayer
) {
    val tts = remember { TextToSpeech(context, null) }
    val scope = rememberCoroutineScope()
    var audioFilePath by rememberSaveable { mutableStateOf("") }
    var startPosition by remember { mutableIntStateOf(0) }
    var spokenWordIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.size(72.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.fillMaxSize(),
            onClick = {
                if (isPlaying) {
                    pauseText(mediaPlayer, scope, onTogglePlayPause)
                    startPosition = mediaPlayer.currentPosition
                    spokenWordIndex = computeWordIndex(readerText, startPosition)
                } else {
                    if (audioFilePath.isEmpty()) {
                        audioFilePath = generateSpeechAudio(context, tts, readerText)
                    }
                    playText(
                        mediaPlayer,
                        audioFilePath,
                        scope,
                        startPosition,
                        spokenWordIndex,
                        onTogglePlayPause
                    )
                }
            }) {
            when (isPlaying) {
                true -> {
                    SetPlayPauseButtonIcons(
                        icon = R.drawable.baseline_pause_circle_outline_24,
                        iconDescription = "Pause speech"
                    )
                }

                false -> {
                    SetPlayPauseButtonIcons(
                        icon = R.drawable.play_button,
                        iconDescription = "Play lesson"
                    )
                }
            }
        }
    }
}


@Composable
private fun SetPlayPauseButtonIcons(
    icon: Int,
    iconDescription: String
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = iconDescription,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(Color.Black)
    )
}

private fun generateSpeechAudio(context: Context, tts: TextToSpeech, text: String): String {
    val fileDir = context.cacheDir
    val audioFile = File(fileDir, "${UUID.randomUUID()}.wav")
    val utteranceID = UUID.randomUUID().toString()
    val textToSpeech = TextToSpeech(context, null)
    tts.synthesizeToFile(text, null, audioFile, UUID.randomUUID().toString())
    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onStart(p0: String?) {
            Log.d("ReaderScreen", "synthesis has started for $p0")
            textToSpeech.speak(
                "Please wait, the audio is loading.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "ready_speech"
            )
        }

        override fun onDone(p0: String?) {
            Log.d("ReaderScreen", "synthesis has finished for $p0")
            textToSpeech.speak(
                "Audio ready",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "loading_speech"
            )
        }

        @Deprecated("Deprecated in Java")
        override fun onError(p0: String?) {
            Log.d("ReaderScreen", "there was an error synthesizing $p0")
            textToSpeech.speak(
                "There was an error loading the audio",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "error_speech"
            )
        }

    })

    return audioFile.absolutePath
}

private fun skipBehind(mediaPlayer: MediaPlayer) {
    val currentPosition = mediaPlayer.currentPosition
    mediaPlayer.seekTo(currentPosition - 5000) // Subtract 5 seconds (5000 milliseconds) from the current position
}

private fun skipAhead(mediaPlayer: MediaPlayer) {
    val currentPosition = mediaPlayer.currentPosition
    mediaPlayer.seekTo(currentPosition + 5000) // Add 5 seconds (5000 milliseconds) to the current position
}

private fun playText(
    mediaPlayer: MediaPlayer,
    filePath: String,
    scope: CoroutineScope,
    startPosition: Int,
    spokenWordIndex: Int,
    onTogglePlayPause: (Boolean) -> Unit
) {

    try {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(filePath)
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }

    try {
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener { mp ->
            mp.seekTo(startPosition)
            mp.start()

            scope.launch {
                mp.setOnCompletionListener {
                    onTogglePlayPause(false)
                }
            }

            onTogglePlayPause(true)
        }
    } catch (e: IllegalStateException) {
        e.printStackTrace()
    }
}

private fun pauseText(
    mediaPlayer: MediaPlayer,
    scope: CoroutineScope,
    onTogglePlayPause: (Boolean) -> Unit
) {
    mediaPlayer.pause()
    onTogglePlayPause(false)
}



private fun computeWordIndex(text: String, position: Int): Int {
    val words = text.split(" ")
    var wordIndex = 0
    var currentPosition = 0
    for (word in words) {
        currentPosition += word.length + 1 // Add 1 for the space character
        if (currentPosition >= position) {
            break
        }
        wordIndex++
    }
    return wordIndex
}

@Preview(showBackground = true)
@Composable
fun ReaderScreenPreview() {
    ReaderScreen(navController = rememberNavController(), viewModel = viewModel())
}