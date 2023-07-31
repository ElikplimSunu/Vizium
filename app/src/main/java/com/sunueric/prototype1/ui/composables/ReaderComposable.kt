package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavController
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.sunueric.prototype1.R
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.theme.dmSans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Composable
fun ReaderScreen(navController: NavController, viewModel: SharedViewModel) {
    val selectedTopicBody by viewModel.selectedTopicBody.observeAsState("")
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(false) }

    val textToSpeech = TextToSpeech(context, null)

    // Generate speech audio only once during the initial composition
    val audioFilePath = remember { mutableStateOf("") }

    // Create a mutable state for the loading state
    var loading by remember { mutableStateOf(true) }

    val readerText = extractTextFromPdf("assets/english_language.pdf")

    LaunchedEffect(Unit) {
        // Generate the audio file path at the beginning
        if (audioFilePath.value.isEmpty()) {
            generateSpeechAudio(context, textToSpeech, readerText).thenAccept { filePath ->
                audioFilePath.value = filePath
                loading = false
            }
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
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
                color = Color(0xFFA3AED0)
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
                color = Color(0xFFA3AED0)
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
            .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            .padding(20.dp)
            .width(353.dp)
            .height(581.dp)) {

            DisplayPdfText(pdfPath = "assets/english_language.pdf")

        }



        Box (modifier = Modifier
            .constrainAs(playButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(bottom = 106.dp)){
            Row (verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        textToSpeech.speak(readerText, TextToSpeech.QUEUE_FLUSH, null, null)
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_previous_button),
                        contentDescription = "Previous line button",
                        tint = Color(0xFF68769F)
                    )
                }

                PauseAndPlayButton(context, isPlaying, onTogglePlayPause = { isPlaying = it }, readerText, audioFilePath.value)

                IconButton(
                    onClick = {
                        textToSpeech.speak(readerText, TextToSpeech.QUEUE_FLUSH, null, null)
                    },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_forward_button),
                        contentDescription = "Next line button",
                        tint = Color(0xFF68769F)
                    )
                }
            }
        }
    }
}

// Displaying the extracted text from the pdf
@Composable
private fun DisplayingExtractedText(readerText: String) {
    Column {
        Text(
            text = "Overview",
            style = TextStyle(
                fontFamily = dmSans,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ).copy(lineHeight = 32.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = "English Language",
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF68769F),
                ).copy(lineHeight = 23.sp)
            )

            Text(
                text = readerText,
                style = TextStyle(
                    fontFamily = dmSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1B2559),
                ).copy(lineHeight = 20.sp)
            )
        }
    }
}

/** This is an asynchronous function that is used to extract text from pdf.
 *  @param pdfPath is the path of the pdf file.
 *  @param callback is the function that is called when the text is extracted.
 *  @return the extracted text from pdf.
 **/
private suspend fun fetchOcrText(pdfPath: String, callback: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        var text = ""
        try {
            text = extractTextFromPdf(pdfPath)
        } catch (e: IOException) {

            e.printStackTrace()
        }

        withContext(Dispatchers.Main) {
            callback(text)
        }
    }
}

/** This is an extension function that is used to extract text from pdf.
 *  It waits for the fetchOcrText function to finish and then returns the text.
 *  @param pdfPath is the path of the pdf file.
 *  @return the extracted text from pdf.
 **/
@Composable
fun DisplayPdfText(pdfPath: String) {
    var loading by remember { mutableStateOf(true) }
    var ocrText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        fetchOcrText(pdfPath) {
            ocrText = it
            loading = false
        }
    }

    return if (loading) {
        LoadingScreen()
    } else {
        DisplayingExtractedText(ocrText)
    }
}

// This is where the loading animation is displayed.
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            RippleLoadingAnimation()
        }
    }
}

/** This function is used to extract text from pdf.
 *  @param path is the path of the pdf file.
 *  @return the extracted text from pdf.
 **/
fun extractTextFromPdf(path: String): String {
    // variable for storing our extracted text
    val stringBuilder = StringBuilder()

    // try and catch block to handle extract data operation.

    try {

        val pdfReader = PdfReader(path)

        val totalNumberOfPages = pdfReader.numberOfPages

        // Loop through each page and extract the text.
        for (pageNumber in 0 until totalNumberOfPages) {
            val textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, pageNumber + 1)
            stringBuilder.append(textFromPage)
        }
        // closing our pdf reader.
        pdfReader.close()

        return stringBuilder.toString()
    }
    // Handling the exception.
    catch (e: Exception) {
        e.printStackTrace()
    }

    return stringBuilder.toString()
}

@Composable
fun PauseAndPlayButton(context: Context = LocalContext.current.applicationContext,
                       isPlaying: Boolean,
                       onTogglePlayPause: (Boolean) -> Unit,
                       readerText: String, audioFilePaths:String) {

    val mediaPlayer = remember { MediaPlayer() }
    val tts = remember { TextToSpeech(context, null) }
    val scope = rememberCoroutineScope()
    var audioFilePath = audioFilePaths
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
                        val future = generateSpeechAudio(context, tts, readerText)
                        // Wait for the audio file generation to complete
                        scope.launch {
                            try {
                                audioFilePath = future.get()
                                playText(
                                    mediaPlayer,
                                    audioFilePath,
                                    scope,
                                    startPosition,
                                    spokenWordIndex,
                                    onTogglePlayPause
                                )
                            } catch (e: Exception) {
                                Log.e("ReaderComposable", "Error generating speech audio", e)
                            }
                        }
                    } else {
                        playText(
                            mediaPlayer,
                            audioFilePath,
                            scope,
                            startPosition,
                            spokenWordIndex,
                            onTogglePlayPause
                        )
                    }
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
                        iconDescription = "Play Song"
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
        colorFilter = ColorFilter.tint(Color(0xFF68769F))
    )
}

private fun generateSpeechAudio(context: Context, tts: TextToSpeech, text: String): CompletableFuture<String> {
    val future = CompletableFuture<String>()
    val fileDir = context.cacheDir
    val audioFile = File(fileDir, "${UUID.randomUUID()}.wav")
    // Set up utterance progress listener to detect completion or errors during synthesis.
    val utteranceID = UUID.randomUUID().toString()

    // Start the synthesis and save to the file.
    tts.synthesizeToFile(text, null, audioFile, utteranceID)

    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
        override fun onDone(utteranceId: String) {
            if (utteranceId == utteranceID) {
                future.complete(audioFile.absolutePath)
            }
        }

        @Deprecated("Deprecated in Java",
            ReplaceWith("future.completeExceptionally(Exception(\"Error occurred during synthesis: \$utteranceId\"))")
        )
        override fun onError(utteranceId: String) {
            future.completeExceptionally(Exception("Error occurred during synthesis: $utteranceId"))
        }

        override fun onStart(utteranceId: String) {
            Log.d("ReaderComposable", "Synthesis started for utteranceId: $utteranceId")
        }
    })

    return future
}

private fun playText(mediaPlayer: MediaPlayer, filePath: String, scope: CoroutineScope,
                     startPosition: Int, spokenWordIndex: Int, onTogglePlayPause: (Boolean) -> Unit) {
    mediaPlayer.reset()
    try {
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

private fun pauseText(mediaPlayer: MediaPlayer, scope: CoroutineScope, onTogglePlayPause: (Boolean) -> Unit) {
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

private fun resumeText(mediaPlayer: MediaPlayer, scope: CoroutineScope) {
    mediaPlayer.start()
}

@Preview (showBackground = true)
@Composable
fun ReaderScreenPreview() {
//    ReaderScreen(navController = rememberNavController())
}