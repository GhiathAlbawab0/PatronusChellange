package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patronuschellange.MainActivity
import com.example.patronuschellange.MainActivityScreen
import com.example.patronuschellange.common.TestTags
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
class UserListViewTest {
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
                    startDestination = MainActivityScreen.UsersListScreen.route
                ) {
                    composable(route = MainActivityScreen.UsersListScreen.route) {
                        val viewmodel: UsersHolderViewmodel = hiltViewModel()
                        val state by viewmodel.state.collectAsState()
                        val showError by viewmodel.showError
                        UserListView(
                            state,
                            { navController.navigate(MainActivityScreen.UsersDetailsScreen.route + "/$it") },
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
    fun userListHeader_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_LIST_HEADER_TEST_TAG).assertExists()
    }

    @Test
    fun userListHeader_TitleExists() {
        composeRule.onNodeWithText("Device holders").assertExists()
    }

    // list test
    @Test
    fun list_countIsOne() {
        composeRule.onAllNodesWithTag(TestTags.USER_ITEM_TEST_TAG).assertCountEquals(1)
    }

    //item test
    @Test
    fun userItemimage_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_IMAGE_TEST_TAG, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun userItemname_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_NAME_TEST_TAG, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun userItemGender_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_GENDER_TEST_TAG, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun userItemPhonenumber_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_PHONENUMBER_TEST_TAG, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun userItemStickers_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_STICKERS_TEST_TAG,useUnmergedTree = true)
            .assertExists()
    }
    @Test
    fun userItemDivider_Exists() {
        composeRule.onNodeWithTag(TestTags.USER_ITEM_DIVIDER_TEST_TAG,useUnmergedTree = true)
            .assertExists()
    }
}