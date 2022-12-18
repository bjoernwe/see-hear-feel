package dev.upaya.shf.ui.session.feelings

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.upaya.shf.exercises.exerciselist.ExerciseId
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label


private const val routeFeelingsSession = "feelings_session"
internal const val routeArgExerciseId = "exerciseId"
internal const val routeFeelingsWithArg = "$routeFeelingsSession/{${routeArgExerciseId}}"


fun NavGraphBuilder.coreFeelingsScreen() {

    composable(
        route = routeFeelingsWithArg,
    ) {

        val coreFeelingViewModel: CoreFeelingViewModel = hiltViewModel()

        val inputEvent by coreFeelingViewModel.inputEventFlow.collectAsState(null)
        val label: Label? by coreFeelingViewModel.labelFlow.collectAsState(null)

        val currentCoreFeeling by coreFeelingViewModel.currentCoreFeeling.collectAsState()
        val round by coreFeelingViewModel.round.collectAsState()
        val resultList by coreFeelingViewModel.resultList.collectAsState()

        LaunchedEffect(inputEvent) {
            when (label) {
                LabelMapKeepDiscard.labelKeep -> coreFeelingViewModel.keepCurrentFeeling()
                LabelMapKeepDiscard.labelDiscard -> coreFeelingViewModel.discardCurrentFeeling()
            }
        }

        CoreFeelingScreen(
            inputEvent = inputEvent,
            label = label,
            currentCoreFeeling = currentCoreFeeling,
            round = round,
            resultList = resultList,
        )
    }

}


fun NavController.navigateToFeelingsSession(exerciseId: ExerciseId) {
    navigate("${routeFeelingsSession}/${exerciseId}")
}
