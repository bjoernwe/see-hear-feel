package dev.upaya.shf.exercises.exerciselist

import dev.upaya.shf.exercises.labelmaps.LabelMap
import dev.upaya.shf.exercises.labelmaps.LabelMapSHF


data class ExerciseConfig(
    val title: String,
    val descriptionShort: String,
    val labelMap: LabelMap = LabelMapSHF,
)
