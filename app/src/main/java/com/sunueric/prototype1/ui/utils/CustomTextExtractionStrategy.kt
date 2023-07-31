package com.sunueric.prototype1.ui.utils

import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.sunueric.prototype1.data.Topic


fun extractingTopicsFromPdf(pdfFilePath: String): List<Topic> {
    val topics = mutableListOf<Topic>()

    try {
        val pdfReader = PdfReader(pdfFilePath)
        val numberOfPages = pdfReader.numberOfPages

        var topicName = ""
        var topicBody = ""
        var capturingTopicBody = false

        for (pageIndex in 1..numberOfPages) {
            val textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, pageIndex)
            val lines = textFromPage.split("\n")

            for (i in lines.indices) {
                if (lines[i].trim().startsWith("CHAPTER", ignoreCase = false)) {
                    //Store the previous topic if it exists
                    if (topicName.isNotEmpty() && topicBody.isNotEmpty()) {
                        topics.add(Topic(topicName, topicBody.trim())) // Trim topicBody to remove extra spaces
                    }

                    //Start of a new topic
                    topicName = lines[i].substringAfter(": ", "").trim()

                    //Reset topicBody and indicate that we are capturing the body
                    topicBody = ""
                    capturingTopicBody = true
                } else if (lines[i].trim() == "OVERVIEW") {
                    // Special case for the overview section... it doesn't have a chapter number
                    topicName = "OVERVIEW"
                    topicBody = ""
                    capturingTopicBody = true
                } else if (capturingTopicBody) {
                    // Capture the topic body
                    topicBody += lines[i] + "\n"
                }
            }
        }

        // Add the last topic
        if (topicName.isNotEmpty() && topicBody.isNotEmpty()) {
            topics.add(Topic(topicName, topicBody.trim())) // Trim topicBody to remove extra spaces
        }

        pdfReader.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return topics
}