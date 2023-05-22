package com.example.patronuschellange.users.presentaion.screen.userdetail

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patronuschellange.MainActivity
import com.example.patronuschellange.MainActivityScreen
import com.example.patronuschellange.common.TestTags
import com.example.patronuschellange.common.TestTags.USER_DETAILS_HEADER_TEST_TAG
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.di.DataModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class)
class UserDetailsViewTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            PatronusChellangeTheme {
                NavHost(
                    navController = navController,
                    startDestination = MainActivityScreen.UsersDetailsScreen.route
                ) {
                    composable(
                        route = MainActivityScreen.UsersDetailsScreen.route,
                    ) {
                        val viewmodel: UserDetailsViewmodel = hiltViewModel()
                        viewmodel.getUserDetails(1)
                        val state by viewmodel.state.collectAsState()
                        val showError by viewmodel.showError
                        UserDetailsView(
                            state,
                            { navController.popBackStack() },
                            showError,
                            viewmodel::closeErrorDialog
                        )
                    }
                }
            }
        }
    }

    //  header test
    @Test
    fun userDetails_header_exists() {
        composeRule.onNodeWithTag(USER_DETAILS_HEADER_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_map_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_MAP_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_image_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_IMAGE_TEST_TAG).assertExists()
    }


    @Test
    fun userDetails_name_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_NAME_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_stickers_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_STICKERS_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_gender_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_GENDER_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_divider_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_DIVIDER_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_phonenumber_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_PHONENUMBER_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_addressTitle_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_ADDRESS_TITLE_TEST_TAG).assertExists()
    }

    @Test
    fun userDetails_address_exist() {
        composeRule.onNodeWithTag(TestTags.USER_DETAILS_ADDRESS_TEST_TAG).assertExists()
    }

}