package dev.upaya.shf

import dev.upaya.shf.labelmaps.LabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF


data class ExerciseConfig(
    val title: String,
    val description: String,
    val labelMap: LabelMap = LabelMapSHF,
)
