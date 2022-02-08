package com.example.knowledgehunt.presentation.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileDialog(showUserDialog: MutableState<Boolean>, profileImageUrl: Uri) {


    if (showUserDialog.value) {
        Dialog(
            onDismissRequest = {
                showUserDialog.value = false
            }
        ) {

            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.onSurface
            ) {

                Column(Modifier.verticalScroll(rememberScrollState())) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        IconButton(onClick = { showUserDialog.value = false }) {
                            Icon(Icons.Outlined.Close, contentDescription = null)
                        }

                        Text(
                            text = "Knowledge Hunt",
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Cursive,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterVertically)
                                .padding(top = 8.dp),
                            color = MaterialTheme.colors.primaryVariant,
                        )

                    }
                    Row(Modifier.padding(12.dp)) {


                        GlideImage(
                            // CoilImage, FrescoImage
                            imageModel = profileImageUrl,
                            modifier = Modifier
                                .size(55.dp)
                                .padding(2.dp)
                                .clip(CircleShape),
                            // shows a shimmering effect when loading an image.
                            shimmerParams = ShimmerParams(
                                baseColor = MaterialTheme.colors.background,
                                highlightColor = MaterialTheme.colors.secondary,
                                durationMillis = 350,
                                dropOff = 0.65f,
                                tilt = 20f
                            ),
                            circularReveal = CircularReveal(800),
                        )
                        Column(Modifier.padding(4.dp)) {
                            Text(
                                text = "AbdelHalim Mahmoud",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(start = 8.dp, bottom = 3.dp)
                            )
                            Text(
                                text = "abdomahmoud20070@gmail.com",
                                fontFamily = FontFamily.Default,
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                            )
                        }
                    }

                    Text(
                        text = "Manage Your Hunt Account",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp)
                            .border(1.dp, Color.Gray.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(onClick = {})
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(8.dp),
                            contentDescription = null
                        )

                        Text(
                            text = "Add another account",
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(8.dp),
                            contentDescription = null
                        )
                        Text(
                            text = "Manage accounts on this device",
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        HuntLogo(
                            Modifier
                                .size(55.dp)
                                .align(CenterVertically)
                                .padding(4.dp)
                        )

                        Text(
                            text = "Privacy Policy",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable(onClick = {})
                                .padding(8.dp)
                        )
                        Text(
                            text = "•"
                        )
                        Text(
                            text = "Terms of service",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable(onClick = {})
                                .padding(8.dp)

                        )
                    }

                }
            }
        }

    }

}