package com.example.patronuschellange.users.presentaion.screen.userdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.patronuschellange.common.theme.GrayDividerColor
import com.example.patronuschellange.common.theme.PatronusChellangeTheme
import com.example.patronuschellange.users.R

@Composable
fun CircularBackButtonView(modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(1.dp, GrayDividerColor),
        shape = RoundedCornerShape(48.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(CenterHorizontally)
                .wrapContentHeight(CenterVertically)
        )
    }
}

@Preview
@Composable
fun CircularBackButtonViewPreview() {
    PatronusChellangeTheme {
        CircularBackButtonView()
    }
}