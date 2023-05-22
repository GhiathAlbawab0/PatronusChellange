package com.example.patronuschellange.users.presentaion.screen.userdetail

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.R
import com.example.patronuschellange.common.TestTags
import com.example.patronuschellange.common.presentation.DialogBoxLoading
import com.example.patronuschellange.common.theme.DarkTextColor
import com.example.patronuschellange.common.theme.GrayDividerColor
import com.example.patronuschellange.common.theme.GrayTextColor
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.presentaion.screen.UserEvents
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsUI
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsViewState
import com.example.patronuschellange.users.presentaion.screen.userlist.CircularUserImageNameView
import com.example.patronuschellange.users.presentaion.screen.userlist.StickersListView
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI

@Composable
fun UserDetailsView(
    state: UserDetailsViewState,
    onBackClick: (UserEvents) -> Unit,
    showErrorDialog: Boolean,
    closeErrorDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val maskedMapModifier = when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> Modifier.fillMaxWidth()
        else -> Modifier.width(320.dp)
    }

    // Error handling Dialog
    if (showErrorDialog) AlertDialog(onDismissRequest = { closeErrorDialog() },
        confirmButton = {
            TextButton(onClick = { closeErrorDialog() }) {
                androidx.compose.material.Text("Ok")
            }
        },
        title = { androidx.compose.material.Text("Error") },
        text = { androidx.compose.material.Text(state.errorMessage) })

    // Loading Dialog
    if (state.isLoading) {
        DialogBoxLoading()
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 22.dp)
                    .fillMaxWidth()
            ) {
                CircularBackButtonView(modifier = Modifier
                    .testTag(TestTags.USER_DETAILS_HEADER_TEST_TAG)
                    .clickable { onBackClick(UserEvents.OnDetailsBackPressed) })
            }

            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MaskedMapView(
                    state.userDetails.currentLatitude,
                    state.userDetails.currentLongitude,
                    maskedMapModifier.testTag(TestTags.USER_DETAILS_MAP_TEST_TAG),
                )
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                CircularUserImageNameView(
                    state.userDetails.firstName,
                    state.userDetails.lastName,
                    state.userDetails.imageUrl,
                    Modifier.testTag(TestTags.USER_DETAILS_IMAGE_TEST_TAG),
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = state.userDetails.firstName + " " + state.userDetails.lastName,
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkTextColor,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .testTag(TestTags.USER_DETAILS_NAME_TEST_TAG),
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                StickersListView(
                    state.userDetails.stickers,
                    Modifier
                        .padding(top = 10.dp)
                        .testTag(TestTags.USER_DETAILS_STICKERS_TEST_TAG),
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = state.userDetails.gender.value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayTextColor,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .testTag(TestTags.USER_DETAILS_GENDER_TEST_TAG),
                )
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .testTag(TestTags.USER_DETAILS_DIVIDER_TEST_TAG),
                    thickness = 1.dp,
                    color = GrayDividerColor
                )
                Text(
                    text = state.userDetails.phoneNumber.replace('-', ' '),
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayTextColor,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .testTag(TestTags.USER_DETAILS_PHONENUMBER_TEST_TAG),
                )
            }

            Text(
                text = stringResource(id = R.string.details_device_holders_address_title),
                style = MaterialTheme.typography.labelSmall,
                color = GrayTextColor,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 14.dp)
                    .testTag(TestTags.USER_DETAILS_ADDRESS_TITLE_TEST_TAG),
            )
            Text(
                text = state.userDetails.address,
                style = MaterialTheme.typography.bodyMedium,
                color = GrayTextColor,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 8.dp)
                    .testTag(TestTags.USER_DETAILS_ADDRESS_TEST_TAG),
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
fun UserDetailsViewPreview() {
    PatronusChellangeTheme {
        UserDetailsView(
            UserDetailsViewState(
                UserDetailsUI(
                    "Savannah",
                    "Nguyen",
                    UserUI.Gender.Female,
                    1,
                    "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                    "123-456-7890 789 07 8 90",
                    listOf(
                        UserUI.Sticker.Fam,
                        UserUI.Sticker.Ban,
                        UserUI.Sticker.Ban,
                    ),
                    37.7749,
                    -122.4194,
                    "123 Main St, 94111 \n San Francisco"
                )
            ),
            {},
            false,
            {},
        )
    }
}