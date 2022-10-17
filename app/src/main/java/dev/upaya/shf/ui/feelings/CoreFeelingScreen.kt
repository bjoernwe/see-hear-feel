package dev.upaya.shf.ui.feelings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.upaya.shf.exercises.labelmaps.LabelMapKeepDiscard
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.KeepScreenOn
import dev.upaya.shf.ui.SetStatusBarColor
import dev.upaya.shf.ui.exercises.SessionViewModel
import dev.upaya.shf.ui.session.FadingText
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun CoreFeelingScreen(
    sessionViewModel: SessionViewModel,
    coreFeelingViewModel: CoreFeelingViewModel = hiltViewModel()
) {

    SetStatusBarColor()
    KeepScreenOn()

    val currentCoreFeeling by coreFeelingViewModel.currentCoreFeeling.collectAsState()
    val inputEvent by sessionViewModel.inputEvent.collectAsState()
    val label: Label? by sessionViewModel.label.collectAsState(initial = null)

    LaunchedEffect(inputEvent) {
        when (label) {
            LabelMapKeepDiscard.labelKeep -> { coreFeelingViewModel.keepCurrentFeeling() }
            LabelMapKeepDiscard.labelDiscard -> { coreFeelingViewModel.discardCurrentFeeling() }
        }
    }

    val finalList by coreFeelingViewModel.finalList.collectAsState()


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (currentCoreFeeling == null) {
            LazyColumn {
                itemsIndexed(finalList) { i, cf ->
                    Text(
                        text = "${i+1}) $cf",
                        fontSize = 20.sp,
                        fontFamily = varelaFontFamily,
                        color = MaterialTheme.colors.secondary,
                    )
                }
            }
        } else {
            Text(
                text = "\"I feel ...\"",
                fontSize = 20.sp,
                fontFamily = varelaFontFamily,
                color = MaterialTheme.colors.secondary,
            )
            Text(
                text = currentCoreFeeling?.uppercase() ?: "",
                fontSize = 40.sp,
                fontFamily = varelaFontFamily,
                color = MaterialTheme.colors.secondary,
            )
            label?.let {
                FadingText(
                    text = "(${it.primary.lowercase()})",
                    key = inputEvent,
                    fontSize = 15.sp,
                )
            }
        }

    }

}
