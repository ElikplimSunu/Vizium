import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunueric.prototype1.data.CoursesViewModel
import com.sunueric.prototype1.data.SharedViewModel
import com.sunueric.prototype1.ui.auth.AuthViewModel
import com.sunueric.prototype1.ui.composables.CoursesScreen
import com.sunueric.prototype1.ui.composables.CustomNavBar
import com.sunueric.prototype1.ui.composables.QuizScreen
import com.sunueric.prototype1.ui.composables.ReaderScreen
import com.sunueric.prototype1.ui.composables.TopicsScreen
import com.sunueric.prototype1.ui.screens.HomepageScreen
import com.sunueric.prototype1.ui.screens.SignInScreen
import com.sunueric.prototype1.ui.screens.SignUpScreen
import com.sunueric.prototype1.ui.screens.onboarding.FirstOnBoardingScreen
import com.sunueric.prototype1.ui.screens.onboarding.SecondOnBoardingScreen
import com.sunueric.prototype1.ui.screens.onboarding.ThirdOnBoardingScreen
import com.sunueric.prototype1.ui.utils.Screens

@Composable
fun Navigation(viewModel: SharedViewModel, authViewModel: AuthViewModel, coursesViewModel: CoursesViewModel) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf<String?>(null) }  // 1. Define the state

    //This is to hide the bottom nav bar on the login and signup screens
    DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { controller, _, _ ->
            currentRoute = controller.currentDestination?.route
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Log.d("TAG", "Navigation: $currentRoute")
                //This is to hide the bottom nav bar on the login and signup screens
                if (currentRoute != null && currentRoute !in listOf(Screens.SignUp.route, Screens.SignIn.route)) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        CustomNavBar(navController = navController)
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                BottomNavGraph(
                    navController = navController,
                    viewModel = viewModel,
                    authViewModel,
                    coursesViewModel
                )
            }
        }
    }
}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel,
    authViewModel: AuthViewModel,
    coursesViewModel: CoursesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignIn.route
    ) {
        composable(route = Screens.SignUp.route)
        {
            SignUpScreen(authViewModel, navController)
        }
        composable(route = Screens.SignIn.route)
        {
            SignInScreen(authViewModel, navController)
        }
        composable(route = Screens.FirstOnBoadingScreen.route)
        {
            FirstOnBoardingScreen(navController)
        }
        composable(route = Screens.SecondOnBoadingScreen.route)
        {
            SecondOnBoardingScreen(navController)
        }
        composable(route = Screens.ThirdOnBoadingScreen.route)
        {
            ThirdOnBoardingScreen(navController)
        }
        composable(route = Screens.Grades.route)
        {
            HomepageScreen(navController, viewModel, coursesViewModel)
        }
        composable(route = Screens.Courses.route)
        {
            CoursesScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Topics.route)
        {
            TopicsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Reader.route) {
            ReaderScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Quiz.route)
        {
            QuizScreen()
        }
        composable(route = Screens.QuizResult.route)
        {
            CoursesScreen(navController = navController, viewModel = viewModel)
        }
    }
}