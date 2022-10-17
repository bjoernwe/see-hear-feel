package dev.upaya.shf.ui.feelings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.exercises.SessionViewModel


@Composable
fun CoreFeelingScreen(
    sessionViewModel: SessionViewModel,
    coreFeelingViewModel: CoreFeelingViewModel = hiltViewModel()
) {

    val coreFeeling by coreFeelingViewModel.currentCoreFeeling.collectAsState()
    val inputEvent by sessionViewModel.inputEvent.collectAsState()
    val label by sessionViewModel.label.collectAsState(initial = Label(primary = ""))

    LaunchedEffect(inputEvent) {
        when (label) {
            LabelMapKeepDiscard.labelKeep -> { coreFeelingViewModel.keepCurrentFeeling() }
            LabelMapKeepDiscard.labelDiscard -> { coreFeelingViewModel.discardCurrentFeeling() }
        }
    }

    Column {
        Text(coreFeeling, color = Color.White)
        Text(label.primary, color = Color.White)
    }

}
