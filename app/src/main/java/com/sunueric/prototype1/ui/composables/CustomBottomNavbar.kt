package com.sunueric.prototype1.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sunueric.prototype1.R

@Composable
fun CustomNavBar(navController: NavHostController){


    val iconToScreens = mapOf(
        R.drawable.home_icon to listOf(
            CustomNavItems.GradesNavItem,
            CustomNavItems.CoursesNavItem,
            CustomNavItems.TopicsNavItem
        ),
        R.drawable.reader_icon to listOf(CustomNavItems.ReaderNavItem),
        R.drawable.quiz_icon to listOf(
            CustomNavItems.QuizNavItem,
            CustomNavItems.QuizResultNavItem
        )
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navStackBackEntry?.destination
    Surface(
        color = Color(0xFF1B2559),
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier
            .width(170.dp)
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconToScreens.forEach { (iconRes, screens) ->
                AddItemGroup(
                    iconRes = iconRes,
                    screens = screens,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun AddItemGroup(
    iconRes: Int,
    screens: List<CustomNavItems>,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    // Check if the current screen belongs to the list of associated screens
    val isSelected = screens.any { it.route == currentDestination?.route }

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .clickable(onClick = {
                // Navigate to the first screen in the list when the icon is clicked
                navController.navigate(screens.first().route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight() // Fill the available height within the Box
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically, // Center the icons vertically
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CustomIndicator(isSelected = isSelected, icon = iconRes, tint = Color(0xFFF6F8FD))
        }
    }
}

@Composable
fun CustomIndicator(isSelected: Boolean, icon: Int, tint: Color) {
    if (isSelected) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier.size(25.dp),
            contentDescription = null,
            tint = tint
        )
    } else {
        Box(
            modifier = Modifier
                .size(15.dp)
                .clip(CircleShape)
                .background(color = Color(0xFFA3AED0))
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CustomNavBarPreview() {
