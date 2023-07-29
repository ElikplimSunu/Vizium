import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.ui.composables.CoursesScreen
import com.sunueric.prototype1.ui.composables.CustomNavBar
import com.sunueric.prototype1.ui.composables.QuizScreen
import com.sunueric.prototype1.ui.composables.ReaderScreen
import com.sunueric.prototype1.ui.composables.TopicsScreen
import com.sunueric.prototype1.ui.screens.HomepageScreen
import com.sunueric.prototype1.ui.utils.Screens

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White), // Set the background color here
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF8FAFB))
                ) {
                    CustomNavBar(navController = navController)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it) // Utilize the padding here
            ) {
                BottomNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Grades.route
    ) {
        composable(route = Screens.Grades.route)
        {
            HomepageScreen(navController)
        }
        composable(route = Screens.Courses.route)
        {
            CoursesScreen(navController = navController)
        }
        composable(route = Screens.Topics.route)
        {
            TopicsScreen(navController = navController)
        }
        composable(route = Screens.Reader.route)
        {
            ReaderScreen(navController = navController)
        }
        composable(route = Screens.Quiz.route)
        {
            QuizScreen()
        }
        composable(route = Screens.QuizResult.route)
        {
            CoursesScreen(navController = navController)
        }
    }
}