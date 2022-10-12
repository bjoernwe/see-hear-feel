package dev.upaya.shf.exercises.labels


data class Label(
    val primary: String,
    val secondary: String? = null,
)


val labelOther = Label("Other")
