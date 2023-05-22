package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.theme.GrayTagBackgroundColor
import com.example.patronuschellange.common.theme.GrayTagTextColor
import com.example.patronuschellange.common.theme.PinkTagBackgroundColor
import com.example.patronuschellange.common.theme.PinkTagTextColor
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI

@Composable
fun RowScope.StickersListView(stickers: List<UserUI.Sticker>, modifier: Modifier = Modifier) {
    stickers.take(2).forEach {
        val backgroundColor =
            if (it is UserUI.Sticker.Fam) GrayTagBackgroundColor else PinkTagBackgroundColor
        val textColor =
            if (it is UserUI.Sticker.Fam) GrayTagTextColor else PinkTagTextColor

        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = modifier.weight(2f, false),
        ) {
            Text(
                text = it.value,
                style = MaterialTheme.typography.bodySmall,
                color = textColor,
                maxLines = 1,
                modifier = Modifier
                    .background(backgroundColor)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}