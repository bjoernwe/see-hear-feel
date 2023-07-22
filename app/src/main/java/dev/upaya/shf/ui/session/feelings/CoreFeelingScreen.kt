package dev.upaya.shf.ui.session.feelings

import androidx.compose.runtime.*
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.inputs.input_events.InputEvent
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.session.KeepScreenOn
import dev.upaya.shf.ui.session.feelings.composables.CurrentCoreFeeling


@Composable
fun CoreFeelingScreen(
    inputEvent: InputEvent?,
    label: Label?,
    currentCoreFeeling: String?,
    round: Int,
    resultList: List<String>,
) {

    SetStatusBarColor()
    KeepScreenOn()

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
