package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.patronuschellange.common.TestTags
import com.example.patronuschellange.common.theme.GrayTagTextColor
import com.example.patronuschellange.common.theme.GrayUserImageBackgroundColor

@Composable
fun CircularUserImageNameView(
    firstName: String,
    lastName: String,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context).data(imageUrl).memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED).build()

    var painterSuccessState by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .size(48.dp),
        shape = RoundedCornerShape(48.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(contentColor = GrayUserImageBackgroundColor),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                imageLoader = context.imageLoader,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                onSuccess = {
                    painterSuccessState = true
                }
            )
            if (!painterSuccessState && firstName.isNotEmpty() && lastName.isNotEmpty())
                Text(
                    text = firstName.first().uppercaseChar()
                        .toString() + lastName.first().uppercaseChar().toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = GrayTagTextColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(),
                )
        }
    }
}