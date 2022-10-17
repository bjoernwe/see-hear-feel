package dev.upaya.shf.ui.session

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun LabelText(label: Label, key: Any? = null) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        FadingText(
            text = label.primary.uppercase(),
            key = key,
            fontFamily = varelaFontFamily
        )

        label.secondary?.let { secondaryLabel ->
            FadingText(
                text = secondaryLabel.uppercase(),
                key = key,
                fontSize = 20.sp,
                fontFamily = varelaFontFamily
            )
        }

    }

}
