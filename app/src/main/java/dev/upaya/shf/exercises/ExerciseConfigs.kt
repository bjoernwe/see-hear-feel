package dev.upaya.shf.exercises

import dev.upaya.shf.labelmaps.LabelMapBreathOther
import dev.upaya.shf.labelmaps.LabelMapLetGo
import dev.upaya.shf.labelmaps.LabelMapPosNegNeutral
import dev.upaya.shf.labelmaps.LabelMapRisingFalling


val exampleExercises: List<ExerciseConfig> = listOf(
    ExerciseConfig(
        title = "SHF",
        description = "Standard See/Hear/Feel",
    ),
    ExerciseConfig(
        title = "Affective Valence",
        description = "Pleasant / Unpleasant / Neutral",
        labelMap = LabelMapPosNegNeutral,
    ),
    ExerciseConfig(
        title = "Mahasi-Style - Basic I",
        description = "Rising/Falling for each breath",
        labelMap = LabelMapRisingFalling,
    ),
    ExerciseConfig(
        title = "Mahasi-Style - Basic II",
        description = "Like \"Basic I\" plus noting every distraction",
        labelMap = LabelMapBreathOther,
    ),
    ExerciseConfig(
        title = "Do Nothing",
        description = "Any intention to control attention is let go of at soon as it is noticed",
        labelMap = LabelMapLetGo,
    ),
    ExerciseConfig(
        title = "Focus on Rest",
        description = "SHF with focus on inner and outer restful states",
    ),
)
