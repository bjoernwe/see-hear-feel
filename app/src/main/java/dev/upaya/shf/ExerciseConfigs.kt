package dev.upaya.shf

import dev.upaya.shf.labelmaps.LabelMapBreathOther


val exampleExercises: List<ExerciseConfig> = listOf(
    ExerciseConfig(
        title = "SHF",
        description = "Standard See/Hear/Feel",
    ),
    ExerciseConfig(
        title = "SHF (steady)",
        description = "See/Hear/Feel while keeping a steady pace",
    ),
    ExerciseConfig(
        title = "SHF (deep)",
        description = "See/Hear/Feel with a focus on deeply \"soaking in\"",
    ),
    ExerciseConfig(
        title = "Valence",
        description = "Pleasant / Unpleasant / Neutral",
    ),
    ExerciseConfig(
        title = "Mahasi-Style - Basic I",
        description = "Rising/Falling for each breath",
    ),
    ExerciseConfig(
        title = "Mahasi-Style - Basic II",
        description = "Like \"Basic I\" plus noting every distraction",
        labelMap = LabelMapBreathOther,
    ),
    ExerciseConfig(
        title = "Do Nothing",
        description = "Any intention to control attention is let go of at soon as it is noticed",
    ),
    ExerciseConfig(
        title = "Focus on Rest",
        description = "SHF with focus on inner and outer restful states",
    ),
)
