package dev.upaya.shf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.upaya.shf.ui.ExerciseList

import dev.upaya.shf.ui.theme.SHFTheme


class ExerciseListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SHFTheme(darkTheme = true, content = { ExerciseList(exampleExercises) }) }
    }

}
