package com.example.patronuschellange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.patronuschellange.common.Constants
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.presentaion.screen.UserEvents
import com.example.patronuschellange.users.presentaion.screen.userdetail.UserDetailsView
import com.example.patronuschellange.users.presentaion.screen.userdetail.UserDetailsViewmodel
import com.example.patronuschellange.users.presentaion.screen.userlist.UserListView
import com.example.patronuschellange.users.presentaion.screen.userlist.UsersHolderViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var userDetailsViewmodel: UserDetailsViewmodel
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PatronusChellangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    userDetailsViewmodel = hiltViewModel()

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
                                ::onEvent,
                                showError,
                                viewmodel::closeErrorDialog
                            )
                        }
                        composable(
                            route = MainActivityScreen.UsersDetailsScreen.route + "/{${Constants.PARAM_DETAILS}}",
                            arguments = listOf(navArgument(Constants.PARAM_DETAILS) {
                                type = NavType.LongType
                            })
                        ) {
                            it.arguments?.getLong(Constants.PARAM_DETAILS)?.let {
                                val state by userDetailsViewmodel.state.collectAsState()
                                val showError by userDetailsViewmodel.showError
                                UserDetailsView(
                                    state,
                                    ::onEvent,
                                    showError,
                                    userDetailsViewmodel::closeErrorDialog
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onEvent(event: UserEvents) {
        when (event) {
            is UserEvents.GetDetails -> {
                userDetailsViewmodel.getUserDetails(event.id)
                navController.navigate(
                    MainActivityScreen.UsersDetailsScreen.route + "/${event.id}"
                )
            }

            UserEvents.OnDetailsBackPressed -> navController.popBackStack()
        }
    }
}
