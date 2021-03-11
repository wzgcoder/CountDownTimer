package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/7 5:17 下午
 */

@Preview
@Composable
fun CountDown() {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        val (resetBtn, pauseBtn, stopBtn, currentTime) = createRefs()
        CurrentTime(modifier = Modifier.constrainAs(currentTime) {
            top.linkTo(parent.top, 300.dp)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        })
        CountDownBtn(
            Modifier
                .constrainAs(resetBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(pauseBtn.start)
                    top.linkTo(pauseBtn.top)
                    bottom.linkTo(pauseBtn.bottom)
                }, R.drawable.ic_reset
        ) {
            System.out.println("重置")
        }
        PauseButton(
            Modifier
                .constrainAs(pauseBtn) {
                    bottom.linkTo(parent.bottom, 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        CountDownBtn(
            Modifier
                .constrainAs(stopBtn) {
                    end.linkTo(parent.end)
                    start.linkTo(pauseBtn.end)
                    top.linkTo(pauseBtn.top)
                    bottom.linkTo(pauseBtn.bottom)
                }, R.drawable.ic_close
        ) {
            System.out.println("停止")
        }
    }
}

@Composable
fun CurrentTime(modifier: Modifier) {
    Text(
        text = "00：00：01",
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold

    )
}

@Composable
fun PauseButton(modifier: Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
            .shadow(50.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pause),
            contentDescription = null,
            Modifier
                .width(90.dp)
                .height(90.dp)
        )
    }

}

@Composable
fun CountDownBtn(modifier: Modifier, resId: Int, click: () -> Unit) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier
            .width(60.dp)
            .height(60.dp)
            .clickable(onClick = click)
    )
}