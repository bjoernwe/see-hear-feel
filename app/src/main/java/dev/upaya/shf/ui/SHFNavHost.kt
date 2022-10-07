package dev.upaya.shf.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.upaya.shf.SHFViewModel
import dev.upaya.shf.ui.session.SessionScreen


@Composable
fun SHFNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = "exercises",
        modifier = modifier
    ) {
        composable(route = "exercises") {
            val vm: SHFViewModel = viewModel()
            ExerciseSceen { cfg ->
                vm.resetInputEvent()  // Workaround: Avoid showing previous label on new screen
                vm.activateLabelMap(cfg.labelMap)
                navController.navigate("session")
            }
        }
        composable(route = "session") {
            SessionScreen()
        }
    }

}
