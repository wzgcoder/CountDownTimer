package com.example.androiddevchallenge

import android.app.TimePickerDialog
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.statu.CompleStatu
import com.example.androiddevchallenge.statu.formatTime

/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/7 5:17 下午
 */


class CountDownProvider : PreviewParameterProvider<CountDownViewModel> {
    var countDownViewModel = CountDownViewModel().apply {
        resetTime(10)
    }
    override val values: Sequence<CountDownViewModel> = listOf(countDownViewModel).asSequence()
}

@PreviewParameter(CountDownProvider::class)
@Composable
fun CountDown(viewModel: CountDownViewModel) {
    if (viewModel.statu is CompleStatu){
        sendSound(LocalContext.current)
    }

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (resetBtn, pauseBtn, stopBtn, timeView) = createRefs()


        TimeView(viewModel, Modifier.constrainAs(timeView) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(pauseBtn.top)
        })
        if (viewModel.statu.resetButtonEnabled()) {
            CountDownBtn(
                Modifier
                    .constrainAs(resetBtn) {
                        start.linkTo(parent.start)
                        end.linkTo(pauseBtn.start)
                        top.linkTo(pauseBtn.top)
                        bottom.linkTo(pauseBtn.bottom)
                    }, R.drawable.ic_reset
            ) {
                viewModel.statu.clickResetButton()
            }
        }

        PauseButton(
            viewModel,
            Modifier
                .constrainAs(pauseBtn) {
                    bottom.linkTo(parent.bottom, 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        if (viewModel.statu.stopButtonEnabled()) {
            CountDownBtn(
                Modifier
                    .constrainAs(stopBtn) {
                        end.linkTo(parent.end)
                        start.linkTo(pauseBtn.end)
                        top.linkTo(pauseBtn.top)
                        bottom.linkTo(pauseBtn.bottom)
                    }, R.drawable.ic_close
            ) {
                viewModel.statu.clickStopButton()
            }
        }
    }
}


@Composable
fun PauseButton(viewModel: CountDownViewModel, modifier: Modifier) {
    Button(
        onClick = { viewModel.statu.clickStarButton() },
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
            .width(60.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        Image(
            painter = painterResource(id = viewModel.statu.startButtonResId()),
            contentDescription = null,
            Modifier.fillMaxSize()
        )
    }

}

@Composable
fun CountDownBtn(modifier: Modifier, resId: Int, click: () -> Unit) {
    Button(
        onClick = click,
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
            .width(40.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            modifier
                .fillMaxSize()
                .clickable(onClick = click)
        )
    }

}

@Composable
fun TimeView(viewModel: CountDownViewModel, modifier: Modifier) {

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { view, hourOfDay, minute ->
            val totalTime = hourOfDay * 60 * 60 + minute * 60
            viewModel.timer.ready(totalTime.toLong())
        }, 24, 0, true
    )

    Box(
        modifier = modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.statu.cuntDownViewShow()) {
            Box(contentAlignment = Alignment.Center) {
                ProgressView(viewModel.statu.getProgress())
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "REMAINING TIME",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${formatTime(viewModel.remainingTime)}",
                        color = MaterialTheme.colors.primary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "TOTAL：${formatTime(viewModel.totalTime)}",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentSize()
                    )
                }
            }

        }

        if (viewModel.statu.setTimeButtonShow()) {
            Button(onClick = { timePickerDialog.show() }) {
                Text(text = "设置时间")
            }
        }


    }
}

@Composable
fun ProgressView(progress: Float) {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        var width = (200 * Math.PI - 3 * 60) / 60
        var stroke = Stroke(
            width = 10.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(3.dp.toPx(), width.dp.toPx())
            )
        )

        //底盘刻度
        drawCircle(
            color = Color(0xFF171C29),
            style = stroke
        )
        //进度刻度
        drawArc(
            brush = Brush.linearGradient(
                0f to Color(0xFF5BC9B0),
                0.5f to Color(0xFF4A53CA),
                1f to Color(0xFF313A57)
            ),
            startAngle = -89f,
            sweepAngle = progress * 360,
            useCenter = false,
            style = stroke
        )
    })
}

private fun sendSound(context :Context){
    val mUri: Uri =
        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) //获取系统默认的notification提示音,Uri:通用资源标志符
    val mRingtone = RingtoneManager.getRingtone(context, mUri)
    mRingtone.play()
}