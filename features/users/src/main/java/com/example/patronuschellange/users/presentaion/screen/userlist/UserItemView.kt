package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.TestTags
import com.example.patronuschellange.common.theme.DarkTextColor
import com.example.patronuschellange.common.theme.GrayDividerColor
import com.example.patronuschellange.common.theme.GrayTagTextColor
import com.example.patronuschellange.common.theme.GrayTextColor
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import timber.log.Timber

@Composable
fun UserItemView(item: UserUI, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            CircularUserImageNameView(
                item.firstName,
                item.lastName,
                item.imageUrl,
                Modifier.testTag(TestTags.USER_ITEM_IMAGE_TEST_TAG)
            )
            Column(
                modifier = Modifier.padding(horizontal = 12.dp),
            ) {
                Row {
                    Text(
                        text = item.firstName + " " + item.lastName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = DarkTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(7f, false)
                            .testTag(TestTags.USER_ITEM_NAME_TEST_TAG)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.gender.value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GrayTagTextColor,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(3f, false)
                            .testTag(TestTags.USER_ITEM_GENDER_TEST_TAG)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = item.phoneNumber.replace('-', ' '),
                        style = MaterialTheme.typography.bodyMedium,
                        color = GrayTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(6f, false)
                            .testTag(TestTags.USER_ITEM_PHONENUMBER_TEST_TAG)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    StickersListView(
                        item.stickers,
                        modifier = Modifier.testTag(TestTags.USER_ITEM_STICKERS_TEST_TAG)
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .testTag(TestTags.USER_ITEM_DIVIDER_TEST_TAG),
            thickness = 1.dp,
            color = GrayDividerColor
        )
    }
}

@Preview
@Composable
fun UserItemViewPreview() {
    PatronusChellangeTheme {
        Timber.plant(Timber.DebugTree())
        UserItemView(
            UserUI(
                "Savannah",
                "Nguyen Nguyen Nguyen",
                UserUI.Gender.Female,
                1,
                "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                "123-456-7890 789 07 8 90",
                listOf(
                    UserUI.Sticker.Fam,
                    UserUI.Sticker.Ban,
                    UserUI.Sticker.Ban,
                )
            )
        )
    }
}
