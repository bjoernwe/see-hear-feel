package dev.upaya.shf.ui.session.feelings

import androidx.compose.runtime.*
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.InputEvent
import dev.upaya.shf.ui.KeepScreenOn
import dev.upaya.shf.ui.SetStatusBarColor


@Composable
fun CoreFeelingScreen(
    inputEvent: InputEvent?,
    label: Label?,
    currentCoreFeeling: String?,
    round: Int,
    resultList: List<String>,
    onKeep: () -> Unit,
    onDiscard: () -> Unit,
) {

    SetStatusBarColor()
    KeepScreenOn()

    LaunchedEffect(inputEvent) {
        when (label) {
            LabelMapKeepDiscard.labelKeep -> onKeep()
            LabelMapKeepDiscard.labelDiscard -> onDiscard()
        }
    }

    if (currentCoreFeeling == null) {

        CoreFeelingsResult(
            resultList = resultList,
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
