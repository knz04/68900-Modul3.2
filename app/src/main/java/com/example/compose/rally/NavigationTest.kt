package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Assert.assertEquals


class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost_verifyOverviewStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }
}


@Test
fun rallyNavHost_clickAllBills_navigateToBills() {
    composeTestRule.onNodeWithContentDescription("All Bills")
        .performScrollTo()
        .performClick()

    val route = navController.currentBackStackEntry?.destination?.route
    assertEquals(route, "bills")
}