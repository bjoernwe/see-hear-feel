package dev.upaya.shf.exercises

import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labelmaps.LabelMapSHF


data class ExerciseConfig(
    val title: String,
    val description: String,
    val labelMap: LabelMap = LabelMapSHF,
)
