package dev.upaya.shf.ui.start

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import dev.upaya.shf.ui.theme.SHFTheme
import org.junit.Rule
import org.junit.Test

class StartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun startScreen_hasStartButton() {
        composeTestRule.setContent {
            SHFTheme {
                StartScreen(
                    onStartButtonClick = {},
                    onSettingsButtonClick = {},
                    onSignInButtonClick = {},
                    signedInUserEmail = "user@email",
                    isLoginEnabled = false,
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Start Session").assertIsDisplayed()
    }
}
