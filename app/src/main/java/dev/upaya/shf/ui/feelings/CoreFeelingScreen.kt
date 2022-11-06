package dev.upaya.shf.ui.feelings

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.KeepScreenOn
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.input.LabelViewModel
import dev.upaya.shf.ui.input.InputViewModel


@Composable
fun CoreFeelingScreen(
    labelViewModel: LabelViewModel,
    inputViewModel: InputViewModel = hiltViewModel(),
    coreFeelingViewModel: CoreFeelingViewModel = hiltViewModel()
) {

    SetStatusBarColor()
    KeepScreenOn()

    val inputEvent by inputViewModel.inputEvent.collectAsState()
    val label: Label? by labelViewModel.label.collectAsState(initial = null)
    val round: Int by coreFeelingViewModel.round.collectAsState()

    LaunchedEffect(inputEvent) {
        when (label) {
            LabelMapKeepDiscard.labelKeep -> { coreFeelingViewModel.keepCurrentFeeling() }
            LabelMapKeepDiscard.labelDiscard -> { coreFeelingViewModel.discardCurrentFeeling() }
        }
    }

    val currentCoreFeeling by coreFeelingViewModel.currentCoreFeeling.collectAsState()

    if (currentCoreFeeling == null) {

        val finalList by coreFeelingViewModel.resultList.collectAsState()

        CoreFeelingsResult(
            resultList = finalList,
        )

    } else {

        CurrentCoreFeeling(
            currentCoreFeeling = currentCoreFeeling,
            inputEvent = inputEvent,
            label = label,
            round = round,
        )

    }

}
