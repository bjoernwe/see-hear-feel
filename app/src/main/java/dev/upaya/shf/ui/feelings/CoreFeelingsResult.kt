package dev.upaya.shf.ui.feelings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.upaya.shf.ui.varelaFontFamily


@Composable
fun CoreFeelingsResult(
    resultList: List<String>,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Result".uppercase(),
            fontSize = 20.sp,
            fontFamily = varelaFontFamily,
            color = MaterialTheme.colors.secondary,
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (resultList.isNotEmpty()) {

            LazyColumn {
                itemsIndexed(resultList) { i, cf ->
                    Text(
                        text = "${i+1}) $cf",
                        fontSize = 20.sp,
                        fontFamily = varelaFontFamily,
                        color = MaterialTheme.colors.secondary,
                    )
                }
            }

        } else {
            Text(
                text = "[ no resonating feelings ]",
                fontSize = 20.sp,
                fontFamily = varelaFontFamily,
                color = MaterialTheme.colors.secondary,
            )
        }

    }

}
