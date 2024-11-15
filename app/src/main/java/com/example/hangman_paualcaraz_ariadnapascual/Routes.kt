import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Routes(val route: String) {
    object SCREEN1 : Routes("SCREEN1")
    object SCREEN2 : Routes("SCREEN2")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SCREEN1.route
    ) {
        composable(route = Routes.SCREEN1.route) { SCREEN1(navController) }
        composable(route = Routes.SCREEN2.route) { Routes.SCREEN2() }
    }
}
