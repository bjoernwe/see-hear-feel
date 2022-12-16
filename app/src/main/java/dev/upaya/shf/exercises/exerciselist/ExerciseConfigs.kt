package dev.upaya.shf.exercises.exerciselist

import dev.upaya.shf.exercises.labelmaps.*


val exampleExercises: Map<String, ExerciseConfig> = mapOf(
    "shf" to ExerciseConfig(
        title = "SHF",
        description = "Standard See/Hear/Feel",
    ),
    "auditor" to ExerciseConfig(
        title = "Meditator & Auditor",
        description = "The auditor has to be convinced that the meditator has really been mindful",
        labelMap = LabelMapAuditor,
    ),
    "valence" to ExerciseConfig(
        title = "Affective Valence",
        description = "Pleasant / Unpleasant / Neutral",
        labelMap = LabelMapPosNegNeutral,
    ),
    "mahasi-1" to ExerciseConfig(
        title = "Mahasi-Style - Basic I",
        description = "Rising/Falling for each breath",
        labelMap = LabelMapRisingFalling,
    ),
    "mahasi-2" to ExerciseConfig(
        title = "Mahasi-Style - Basic II",
        description = "Like \"Basic I\" plus noting every distraction",
        labelMap = LabelMapBreathOther,
    ),
    "do-nothing" to ExerciseConfig(
        title = "Do Nothing",
        description = "Any intention to control attention is let go of at soon as it is noticed",
        labelMap = LabelMapLetGo,
    ),
    "rest" to ExerciseConfig(
        title = "Focus on Rest",
        description = "SHF with focus on inner and outer restful states",
    ),
    "sibam" to ExerciseConfig(
        title = "SIBAM",
        description = "Noting according to the five categories of experience used in Somatic Experiencing (SE)",
        labelMap = LabelMapSibam,
    ),
    "core-feelings" to ExerciseConfig(
        title = "Core Feelings",
        description = "Exploration of the Core Feelings described by Douglas Tataryn",
        labelMap = LabelMapKeepDiscard,
        route = ExerciseRoute.FEELINGS,
    ),
)
