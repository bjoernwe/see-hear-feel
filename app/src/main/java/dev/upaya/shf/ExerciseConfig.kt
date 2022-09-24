package dev.upaya.shf

import dev.upaya.shf.labelmaps.ILabelMap
import dev.upaya.shf.labelmaps.LabelMapSHF


data class ExerciseConfig(
    val title: String,
    val description: String,
    val labelMap: ILabelMap = LabelMapSHF,
)
