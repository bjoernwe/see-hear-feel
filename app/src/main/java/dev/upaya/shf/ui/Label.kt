package dev.upaya.shf.ui


data class Label(
    val primary: String,
    val secondary: String? = null,
)


val labelOther = Label("Other")
