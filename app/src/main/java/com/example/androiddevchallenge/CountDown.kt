package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/7 5:17 下午
 */

@Preview
@Composable
fun CountDown() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Red)
        ) {
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment  = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
            )
            Text(
                text = "00：01：59",
                color = Color.Green
            )

            Image(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp))

        }


    }
}