package dev.upaya.shf.exercises.exerciselist

import dev.upaya.shf.exercises.labelmaps.*


val exampleExercises: List<ExerciseConfig> = listOf(
    ExerciseConfig(
        title = "SHF",
        description = "Standard See/Hear/Feel",
    ),
    ExerciseConfig(
        title = "Meditator & Auditor",
        description = "The auditor has to be convinced that the meditator has really been mindful",
        labelMap = LabelMapAuditor,
    ),
    ExerciseConfig(
        title = "SHF & Advanced Auditor",
        description = "The auditor has to be convinced of Clarity & Equanimity in center and periphery",
        labelMap = LabelMapSHFAuditor,
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
    ExerciseConfig(
        title = "Core Feelings",
        description = "Exploration of the Core Feelings described by Douglas Tataryn",
        labelMap = LabelMapKeepDiscard,
        route = ExerciseRoute.FEELINGS,
    ),
)
