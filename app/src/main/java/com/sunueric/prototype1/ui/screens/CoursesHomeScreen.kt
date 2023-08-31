//package com.sunueric.prototype1.ui.screens
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sunueric.prototype1.R
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun CourseHomeScreen() {
//    var searchQuery by remember { mutableStateOf("") }
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f
//    ) {
//        // provide pageCount
//        4
//    }
//
//    val cardWidth = LocalContext.current.resources.displayMetrics.widthPixels * 0.6f
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Row(modifier = Modifier.fillMaxWidth()) {
//                Text(
//                    text = "Hi, Christina",
//                    style = TextStyle(
//                        fontSize = 24.sp,
//                        lineHeight = 34.sp,
//                        fontWeight = FontWeight(400),
//                        color = Color(0xFF181818),
//                    )
//                )
//
//                Spacer(modifier = Modifier.width(30.dp))
//
//                /*TODO Add the notification button*/
//            }
//
//            Text(
//                text = "What do you want to learn today?",
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    lineHeight = 24.sp,
//                    fontWeight = FontWeight(400),
//                    color = Color(0xFF888888),
//                )
//            )
//
//            Spacer(modifier = Modifier.height(18.dp))
//
//            SearchBox(
//                value = searchQuery,
//                onValueChange = {
//                    searchQuery = it
//                    // Handle search or do something with the query
//                },
//                modifier = Modifier.fillMaxWidth())
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//            Text(
//                text = "Recent learning",
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    lineHeight = 24.sp,
//                    fontWeight = FontWeight(400),
//                    color = Color(0xFF181818),
//                )
//            )
//
//            HorizontalPager(
//                state = pagerState,
//                beyondBoundsPageCount = 1,
//                contentPadding = PaddingValues(start = (cardWidth * 0.1f).dp, end = (cardWidth * 0.1f).dp),
//            ) { page ->
//                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
//                val cardSize by animateFloatAsState(
//                    targetValue = if (pageOffset != 0.0f) 0.8f else 1f, label = "Card animation",
//                    animationSpec = tween(250)
//                )
//                Card(modifier = Modifier
//                    .width(cardWidth.dp)
//                    .height(200.dp)
//                    .graphicsLayer {
//                        scaleX = cardSize
//                        scaleY = cardSize
//                    },
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFFDFDFD),
//                        contentColor = Color(0xFF181818)
//                    ),
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .width(cardWidth.dp)
//                            .padding(20.dp)
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 20.dp)
//                        ) {
//                            Image(
//                                painter = painterResource(id = ),
//                                contentDescription = "Course Image",
//                                modifier = Modifier
//                                    .height(100.dp)
//                                    .width(100.dp)
//                            )
//
//                            Spacer(modifier = Modifier.width(20.dp))
//
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .weight(1f)
//                            ) {
//                                Text(
//                                    text = "Course name",
//                                    style = TextStyle(
//                                        fontSize = 18.sp,
//                                        lineHeight = 28.sp,
//                                        fontWeight = FontWeight(400),
//                                        color = Color(0xFF181818),
//                                    )
//                                )
//
//                                Spacer(modifier = Modifier.height(10.dp))
//
//                                Text(
//                                    text = recentTopics[page].courseTopic,
//                                    style = TextStyle(
//                                        fontSize = 14.sp,
//                                        lineHeight = 24.sp,
//                                        fontWeight = FontWeight(400),
//                                        color = Color(0xFF888888),
//                                    )
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(20.dp))
//
//                            Text(
//                                text = "course progress",
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    lineHeight = 24.sp,
//                                    fontWeight = FontWeight(400),
//                                    color = Color(0xFF888888),
//                                ),
//                                textAlign = TextAlign.End,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .weight(1f)
//                            )
//                        }
//                    }
//
//                }
//
//            }
//
//            Spacer(modifier = Modifier.height(25.dp))
//
//            SpinnerWithContent()
//        }
//    }
//}
//
//@Composable
//fun SearchBox(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    hint: String = "Search for a course"
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        modifier = modifier,
//        placeholder = { Text(text = hint) },
//        singleLine = true,
//        leadingIcon = { Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "search icon") }
//    )
//}
//
//@Composable
//fun SpinnerWithContent() {
//    var selectedOption by remember { mutableStateOf<SubCourse?>(null) }
//    var isDropdownExpanded by remember { mutableStateOf(false) }
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        // TextButton that shows the selected option and opens the dropdown
//        Button(
//            onClick = {isDropdownExpanded = true},
//            modifier = Modifier
//                .height(70.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(size = 6.dp),
//            colors = ButtonDefaults.buttonColors(
//                contentColor = Color.Black
//            )
//        ) {
//            Text(
//                text = selectedOption?.subCourseName ?: "Choose a Sub Course",
//                style = TextStyle(
//                    fontSize = 24.sp,
//                    lineHeight = 28.sp,
//                    fontWeight = FontWeight(400),
//                    color = Color(0xFF181818),
//                    textAlign = TextAlign.Center,
//                )
//            )
//        }
//
//        // Dropdown menu
//        DropdownMenu(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            expanded = isDropdownExpanded,
//            onDismissRequest = { isDropdownExpanded = false }
//        ) {
//            subCourses.forEach { subCourse ->
//                DropdownMenuItem(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    onClick = {
//                        selectedOption = subCourse
//                        isDropdownExpanded = false // Close the dropdown
//                    },
//                    text = { Text(subCourse.subCourseName) }
//                )
//            }
//        }
//
//        // Spacer for separation
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display content based on the selected option
//        when (selectedOption?.subCourseName) {
//            null -> Text("Please select an option from the dropdown.")
//            else -> SubCoursePager(selectedOption!!.subCourseItem)
//        }
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
//@Composable
//fun SubCoursePager() {
//    val cardWidth = LocalContext.current.resources.displayMetrics.widthPixels * 0.6f
//
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f
//    ) {
//        // provide pageCount
//        4
//    }
//
//    HorizontalPager(
//        state = pagerState,
//        beyondBoundsPageCount = 1,
//        contentPadding = PaddingValues(start = (cardWidth * 0.1f).dp, end = (cardWidth * 0.1f).dp),
//    ) { page ->
//        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
//        val cardSize by animateFloatAsState(
//            targetValue = if (pageOffset != 0.0f) 0.8f else 1f, label = "Card animation",
//            animationSpec = tween(250)
//        )
//        Card(modifier = Modifier
//            .width(cardWidth.dp)
//            .height(200.dp)
//            .graphicsLayer {
//                scaleX = cardSize
//                scaleY = cardSize
//            },
//            onClick = { /*TODO*/ },
//            colors = CardDefaults.cardColors(
//                containerColor = Color(0xFFFDFDFD),
//                contentColor = Color(0xFF181818)
//            ),
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .width(cardWidth.dp)
//                    .padding(20.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 20.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = subCourseItems[page].subCourseImage),
//                        contentDescription = "Course Image",
//                        modifier = Modifier
//                            .height(100.dp)
//                            .width(100.dp)
//                    )
//
//                    Spacer(modifier = Modifier.width(20.dp))
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//
//                        Spacer(modifier = Modifier.height(10.dp))
//
//                        Text(
//                            text = subCourseItems[page].subCourseTopic,
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                lineHeight = 24.sp,
//                                fontWeight = FontWeight(400),
//                                color = Color(0xFF888888),
//                            )
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.width(20.dp))
//
//                    Text(
//                        text = "sdfsd",
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            lineHeight = 24.sp,
//                            fontWeight = FontWeight(400),
//                            color = Color(0xFF888888),
//                        ),
//                        textAlign = TextAlign.End,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    )
//                }
//            }
//
//        }
//
//    }
//}
