package com.example.patronuschellange

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.patronuschellange.common.Constants
import com.example.patronuschellange.common.TestTags
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.di.DataModule
import com.example.patronuschellange.users.presentaion.screen.userdetail.UserDetailsView
import com.example.patronuschellange.users.presentaion.screen.userdetail.UserDetailsViewmodel
import com.example.patronuschellange.users.presentaion.screen.userlist.UserListView
import com.example.patronuschellange.users.presentaion.screen.userlist.UsersHolderViewmodel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * end to end test simulate the scenario:
 * fetch the users list
 * click the first item
 * navigate to the details screen
 */
@HiltAndroidTest
@UninstallModules(DataModule::class)
class ListToDetailsEndToEndTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            PatronusChellangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MainActivityScreen.UsersListScreen.route
                    ) {
                        composable(MainActivityScreen.UsersListScreen.route) {
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
                        composable(
                            route = MainActivityScreen.UsersDetailsScreen.route + "/{${Constants.PARAM_DETAILS}}",
                            arguments = listOf(
                                navArgument(Constants.PARAM_DETAILS) {
                                    type = NavType.LongType
                                }
                            )
                        ) {
                            it.arguments?.getLong(Constants.PARAM_DETAILS)?.let {
                                val viewmodel: UserDetailsViewmodel = hiltViewModel()
                                viewmodel.getUserDetails(it)
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
        }
    }


    @Test
    fun getList_clickFirstItem_navigateToDetails() {
        //check items visible
        composeRule.onAllNodesWithTag(TestTags.USER_ITEM_TEST_TAG).assertCountEquals(1)
        //click the first item
        composeRule.onAllNodesWithTag(TestTags.USER_ITEM_TEST_TAG)[0].performClick()

        assertThat(navController.currentDestination?.route)
            .isEqualTo(MainActivityScreen.UsersDetailsScreen.route + "/{${Constants.PARAM_DETAILS}}")
    }
}