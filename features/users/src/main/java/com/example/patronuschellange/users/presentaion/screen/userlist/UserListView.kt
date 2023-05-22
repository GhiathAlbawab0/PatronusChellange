package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.R
import com.example.patronuschellange.common.TestTags.USER_ITEM_TEST_TAG
import com.example.patronuschellange.common.TestTags.USER_LIST_HEADER_TEST_TAG
import com.example.patronuschellange.common.presentation.DialogBoxLoading
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.presentaion.screen.UserEvents
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UsersListViewState

@Composable
fun UserListView(
    state: UsersListViewState,
    onItemClick: (UserEvents) -> Unit,
    showErrorDialog: Boolean,
    closeErrorDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Error handling Dialog
    if (showErrorDialog) AlertDialog(onDismissRequest = { closeErrorDialog() },
        confirmButton = {
            TextButton(onClick = { closeErrorDialog() }) {
                Text("Ok")
            }
        },
        title = { Text("Error") },
        text = { Text(state.errorMessage) })

    // Loading Dialog
    if (state.isLoading) DialogBoxLoading()

    Column(
        modifier = modifier
            .padding(start = 24.dp)
            .fillMaxSize()
    ) {
        UserListHeaderView(
            title = stringResource(id = R.string.list_device_holders_header_title),
            Modifier.testTag(USER_LIST_HEADER_TEST_TAG)
        )
        LazyColumn {
            state.users.forEach {
                item {
                    UserItemView(
                        it,
                        Modifier
                            .testTag(USER_ITEM_TEST_TAG)
                            .clickable { onItemClick(UserEvents.GetDetails(it.id)) })
                }
            }
        }
    }
}

@Preview
@Composable
fun UserListViewPreview() {
    PatronusChellangeTheme {
        UserListView(
            UsersListViewState(
                listOf(
                    UserUI(
                        "Savannah Savannah Savannah Savannah",
                        "Nguyen",
                        UserUI.Gender.Female,
                        1,
                        "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                        "123-456-7890",
                        listOf(
//                    UserUI.Sticker.Fam,
                            UserUI.Sticker.Ban
                        )
                    ),
                    UserUI(
                        "Savannah",
                        "Nguyen",
                        UserUI.Gender.Female,
                        1,
                        "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                        "123-456-7890",
                        listOf(
                            UserUI.Sticker.Fam,
                            UserUI.Sticker.Ban
                        )
                    ),
                    UserUI(
                        "Savannah",
                        "Nguyen",
                        UserUI.Gender.Female,
                        1,
                        "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                        "123-456-7890",
                        listOf(
                            UserUI.Sticker.Fam,
//                    UserUI.Sticker.Ban
                        )
                    ),
                    UserUI(
                        "Savannah",
                        "Nguyen",
                        UserUI.Gender.Female,
                        1,
                        "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                        "123-456-7890",
                        listOf(
//                        UserUI.Sticker.Fam,
//                    UserUI.Sticker.Ban
                        )
                    )
                )
            ),
            {},
            false,
            {},
        )

    }
}