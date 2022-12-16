package dev.upaya.shf.exercises.exerciselist

import dev.upaya.shf.exercises.labelmaps.*


enum class ExerciseID {
    SHF, AUDITOR, VALENCE, MAHASI_1, MAHASI_2, DO_NOTHING, REST, SIBAM, CORE_FEELINGS,
}


val exampleExercises: Map<ExerciseID, ExerciseConfig> = mapOf(
    ExerciseID.SHF to ExerciseConfig(
        title = "SHF",
        description = "Standard See/Hear/Feel",
    ),
    ExerciseID.AUDITOR to ExerciseConfig(
        title = "Meditator & Auditor",
        description = "The auditor has to be convinced that the meditator has really been mindful",
        labelMap = LabelMapAuditor,
    ),
    ExerciseID.VALENCE to ExerciseConfig(
        title = "Affective Valence",
        description = "Pleasant / Unpleasant / Neutral",
        labelMap = LabelMapPosNegNeutral,
    ),
    ExerciseID.MAHASI_1 to ExerciseConfig(
        title = "Mahasi-Style - Basic I",
        description = "Rising/Falling for each breath",
        labelMap = LabelMapRisingFalling,
    ),
    ExerciseID.MAHASI_2 to ExerciseConfig(
        title = "Mahasi-Style - Basic II",
        description = "Like \"Basic I\" plus noting every distraction",
        labelMap = LabelMapBreathOther,
    ),
    ExerciseID.DO_NOTHING to ExerciseConfig(
        title = "Do Nothing",
        description = "Any intention to control attention is let go of at soon as it is noticed",
        labelMap = LabelMapLetGo,
    ),
    ExerciseID.REST to ExerciseConfig(
        title = "Focus on Rest",
        description = "SHF with focus on inner and outer restful states",
    ),
    ExerciseID.SIBAM to ExerciseConfig(
        title = "SIBAM",
        description = "Noting according to the five categories of experience used in Somatic Experiencing (SE)",
        labelMap = LabelMapSibam,
    ),
    ExerciseID.CORE_FEELINGS to ExerciseConfig(
        title = "Core Feelings",
        description = "Exploration of the Core Feelings described by Douglas Tataryn",
        labelMap = LabelMapKeepDiscard,
        route = ExerciseRoute.FEELINGS,
    ),
)
