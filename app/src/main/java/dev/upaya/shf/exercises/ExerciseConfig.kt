package dev.upaya.shf.exercises

import dev.upaya.shf.exercises.labels.LabelMap
import dev.upaya.shf.exercises.labels.LabelMapSHF


data class ExerciseConfig(
    val title: String,
    val description: String,
    val labelMap: LabelMap = LabelMapSHF,
)
