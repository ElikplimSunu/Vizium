//package com.sunueric.prototype1.ui.utils
//
//import android.adservices.topics.Topic
//import com.itextpdf.text.pdf.BaseFont
//import com.itextpdf.text.pdf.parser.ImageRenderInfo
//import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy
//import com.itextpdf.text.pdf.parser.TextExtractionStrategy
//import com.itextpdf.text.pdf.parser.TextRenderInfo
//
//class CustomTextExtractionStrategy: TextExtractionStrategy {
//    private var currentText = StringBuilder()
//    private var previousFont: BaseFont? = null
//    private var previousFontSize = 0f
//    private var previousFontWeight = false
//    private val topics = mutableListOf<Topic>()
//
//    override fun beginTextBlock() {}
//
//    override fun endTextBlock() {}
//
//    override fun renderImage(imageRenderInfo: ImageRenderInfo) {}
//    override fun getResultantText(): String {
//        TODO("Not yet implemented")
//    }
//
//    override fun renderText(textRenderInfo: TextRenderInfo) {
//        val text = textRenderInfo.text
//        val currentFont = textRenderInfo.font.baseFont
//        val currentFontSize = textRenderInfo.fontSize
//        val currentFontWeight = textRenderInfo.textRenderMode == PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE
//
//        if (currentFont != previousFont || currentFontSize != previousFontSize || currentFontWeight != previousFontWeight) {
//            // Check if the current text represents a topic
//            val isTopic = currentFontSize == 24f && currentFontWeight
//
//            if (currentText.isNotEmpty()) {
//                val content = currentText.toString().trim()
//                if (isTopic) {
//                    topics.add(Topic(content, "", currentFontSize, currentFontWeight))
//                } else if (topics.isNotEmpty()) {
//                    topics.last().body += "$content "
//                }
//            }
//
//            currentText.clear()
//        }
//
//        currentText.append(text)
//        previousFont = currentFont
//        previousFontSize = currentFontSize
//        previousFontWeight = currentFontWeight
//    }
//
//    fun getTopics(): List<Topic> {
//        return topics
//    }
//}