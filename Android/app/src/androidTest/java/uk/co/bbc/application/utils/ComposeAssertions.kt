package uk.co.bbc.application.utils

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue

object ComposeAssertions {

    fun isDisplayed(composeTestRule: ComposeTestRule, testTag: String) {
        composeTestRule.onNodeWithTag(testTag).assertIsDisplayed()
    }

    fun isDisplayedWithText(composeTestRule: ComposeTestRule, text: String) {
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    fun assertNodeCountWithContentDescription(composeTestRule: ComposeTestRule, text: String, expectedCount: Int){
        composeTestRule.onAllNodesWithContentDescription(text).assertCountEquals(expectedCount)
    }

    fun doesNotExist(composeTestRule: ComposeTestRule, testTag: String) {
        composeTestRule.onNodeWithTag(testTag).assertDoesNotExist()
    }

    fun assertNodeExistsWithText(composeTestRule: ComposeTestRule, text: String, substring: Boolean = false, ignoreCase: Boolean = false) {
        composeTestRule.onNodeWithText(text, substring = substring, ignoreCase = ignoreCase)
            .assertExists()
    }

    fun assertNodeCountWithText(composeTestRule: ComposeTestRule, text: String, expectedCount: Int) {
        composeTestRule.onAllNodesWithText(text).assertCountEquals(expectedCount)
    }

    fun assertLastUpdatedTimeIsCorrect(composeTestRule: ComposeTestRule, tag: String, dateRegex: Regex) {
        val timeNode = composeTestRule.onNodeWithTag(tag)
        val timeText = timeNode.fetchSemanticsNode().config[SemanticsProperties.Text].joinToString()
        assertTrue(dateRegex.matches(timeText))
    }

    fun assertLastUpdatedTimeHasChanged(composeTestRule: ComposeTestRule, tag: String, initialTimeText: String, dateRegex: Regex) {
        val updatedTimeNode = composeTestRule.onNodeWithTag(tag)
        val updatedTimeText = updatedTimeNode.fetchSemanticsNode().config[SemanticsProperties.Text].joinToString()
        assertNotEquals("Last updated time should change after refresh", initialTimeText, updatedTimeText)
        assertTrue(dateRegex.matches(updatedTimeText))
    }

}
