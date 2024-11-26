package uk.co.bbc.application.utils

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput

object ComposeActions {

    fun performClickOnNodeWithTag(composeTestRule: ComposeTestRule, testTag: String) {
        composeTestRule.onNodeWithTag(testTag).performClick()
    }

    fun performClickOnNodeWithText(composeTestRule: ComposeTestRule, testTag: String) {
        composeTestRule.onNodeWithText(testTag).performClick()
    }

    fun waitForLoadingSpinnerToDisappear(composeTestRule: ComposeTestRule, tag: String, timeoutMillis: Long = 3000) {
        composeTestRule.waitUntil(timeoutMillis = timeoutMillis) {
            composeTestRule.onAllNodesWithContentDescription(tag).fetchSemanticsNodes().isEmpty()
        }
    }

    fun scrollToNodeWithText(composeTestRule: ComposeTestRule, text: String, substring: Boolean = false, ignoreCase: Boolean = false) {
        composeTestRule.onNodeWithText(text, substring = substring, ignoreCase = ignoreCase)
            .performScrollTo()
    }


}