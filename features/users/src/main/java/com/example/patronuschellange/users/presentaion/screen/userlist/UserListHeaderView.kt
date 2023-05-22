package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.theme.DarkTextColor
import com.example.patronuschellange.common.theme.PatronusChellangeTheme

@Composable
fun UserListHeaderView(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style=MaterialTheme.typography.titleLarge,
        color = DarkTextColor,
        modifier =modifier.fillMaxWidth()
            .padding(top = 29.dp, bottom = 17.dp)
    )
}

@Preview
@Composable
fun UserListHeaderViewPreview() {
    PatronusChellangeTheme() {
        UserListHeaderView("Device holders")
    }
}