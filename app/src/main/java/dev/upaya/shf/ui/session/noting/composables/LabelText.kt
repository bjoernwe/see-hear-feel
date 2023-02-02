package dev.upaya.shf.ui.session.noting.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.upaya.shf.exercises.labels.Label
import dev.upaya.shf.ui.theme.SHFTheme
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun LabelText(
    label: Label,
    modifier: Modifier = Modifier,
    primaryColor: Color = MaterialTheme.colors.secondary,
    secondaryColor: Color? = null,
    key: Any? = null,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {

        FadingText(
            text = label.primary.uppercase(),
            color = primaryColor,
            fontFamily = varelaFontFamily,
            key = key,
        )

        label.secondary?.let { secondaryLabel ->
            FadingText(
                text = secondaryLabel.uppercase(),
                color = secondaryColor ?: primaryColor,
                fontSize = 20.sp,
                fontFamily = varelaFontFamily,
                key = key,
            )
        }

    }

}


@Preview
@Composable
fun LabelTextPreview() {
    SHFTheme(darkTheme = true) {
        LabelText(
            label = Label(primary = "Primary", secondary = "Secondary"),
            secondaryColor = MaterialTheme.colors.secondaryVariant,
        )
    }
}
