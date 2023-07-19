package com.sunueric.prototype1.ui.composables

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.sunueric.prototype1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderScreen() {
    val context = LocalContext.current

    val textToSpeech = TextToSpeech(context, null)

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

            DisplayPdfText(pdfPath = "assets/creative-arts-primary-4-6gh.pdf")

        }
        val readerText = extractTextFromPdf("assets/creative-arts-primary-4-6gh.pdf")

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

                PauseAndPlayButton(context, readerText, textToSpeech)

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
private fun DisplayingExtractedText() {
    Column {
        Text(
            text = "Overview",
            style = TextStyle(
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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF68769F),
                ).copy(lineHeight = 23.sp)
            )

            Text(
                text = extractTextFromPdf("assets/creative-arts-primary-4-6gh.pdf"),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1B2559),
                ).copy(lineHeight = 20.sp)
            )
        }
    }
}

/** This is an asychronous function that is used to extract text from pdf.
 *  @param path is the path of the pdf file.
 *  @return the extracted text from pdf.
 **/
private suspend fun fetchOcrText(pdfPath: String, callback: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        var text = ""
        try {
            text = extractTextFromPdf(pdfPath)
        } catch (e: IOException) {
            // Handle any exceptions here if needed
            e.printStackTrace()
        }

        withContext(Dispatchers.Main) {
            callback(text)
        }
    }
}

/** This is an extension function that is used to extract text from pdf.
 *  It waits for the fetchOcrText function to finish and then returns the text.
 *  @param path is the path of the pdf file.
 *  @return the extracted text from pdf.
 **/
@Composable
fun DisplayPdfText(pdfPath: String): Unit {
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
        DisplayingExtractedText()
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
    val pdfReader = PdfReader(path)
    val totalPageNum = pdfReader.numberOfPages
    val stringBuilder = StringBuilder()
    // on below line we are creating a
    // variable for storing our extracted text
    val extractedText = ""

    // on below line we are running a try and catch block
    // to handle extract data operation.
    try {

        val pdfReader = PdfReader(path)

        val n = pdfReader.numberOfPages

        // Loop through each page and extract the text.
        for (pageNumber in 1..1) {
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

    return extractedText
}

private const val playIcon = 1
private const val pauseIcon = 2

@Composable
fun PauseAndPlayButton(context: Context = LocalContext.current.applicationContext,
                       readerText: String, textToSpeech: TextToSpeech = TextToSpeech(context, null)
) {

    val mediaPlayer = remember { MediaPlayer() }
    val tts = remember { TextToSpeech(context, null) }
    val scope = rememberCoroutineScope()
    var isPlaying by remember { mutableStateOf(false) }
    var audioFilePath by rememberSaveable { mutableStateOf("") }
    var startPosition by remember { mutableIntStateOf(0) }
    var spokenWordIndex by remember { mutableIntStateOf(0) }

    // This is used to remember the icon of the button
    // Its values are playIcon, loadingBar, and pauseIcon
    // Initially display the playIcon
    var buttonIcon by remember {
        mutableIntStateOf(playIcon)
    }

    OutlinedButton(
        modifier = Modifier
            .size(72.dp),
        shape = CircleShape,
        //contentPadding = PaddingValues(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = {
            textToSpeech.speak(
                readerText,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "reader_text_placeholder"
            )

            if (buttonIcon == playIcon) {
                // If the current icon is play Icon
                // change it to pause icon
                buttonIcon = pauseIcon
            } else if (buttonIcon == pauseIcon) {
                // If the current icon is pause icon
                // change it to play icon
                buttonIcon = playIcon
            }

//            when (buttonIcon) {
//                playIcon -> {
//                    // If the current icon is play Icon, change it to pause icon and speak the text
//                    buttonIcon = pauseIcon
//                    startPosition = textToSpeech.currentPosition
//                    textToSpeech.speak(readerText, TextToSpeech.QUEUE_FLUSH, null, "reader_text_placeholder")
//                }
//                pauseIcon -> {
//                    // If the current icon is pause Icon, change it to play icon and pause the speech
//                    buttonIcon = playIcon
//                    textToSpeech.stop()
//                }
//            }
        }
    ) {

        when (buttonIcon) {
            playIcon -> {
                // Set the play icon
                SetButtonIcons(
                    icon = R.drawable.play_button,
                    iconDescription = "Play Song"
                )

                // If the song is loaded, pause the actual song
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                }
            }
            pauseIcon -> {
                // Set the pause icon
                SetButtonIcons(R.drawable.baseline_pause_circle_outline_24, iconDescription = "Pause speech")

                // If the song is loaded, play the actual song
                if (!(textToSpeech.isSpeaking)) {
                    textToSpeech.speak(
                        readerText,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "reader_text_placeholder")
                }
            }
        }
    }
}


@Composable
private fun SetButtonIcons(
    icon: Int,
    iconDescription: String
) {
    Icon(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = icon),
        contentDescription = iconDescription,
        tint = Color(0xFF68769F)
    )
}




@Preview (showBackground = true)
@Composable
fun ReaderScreenPreview() {
    ReaderScreen()
}