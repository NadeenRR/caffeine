To start Navigation in jetpack compose, we need

1. navController ->  to remember where I found in stack
2. navHost -> contains the composable

val navController = rememberNavController()
NavHost(navController, startDestination = "homeScreen") {
composable("homeScreen") {
HomeScreen()
}
}