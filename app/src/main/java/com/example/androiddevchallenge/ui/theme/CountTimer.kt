package com.example.androiddevchallenge.ui.theme

import android.animation.ValueAnimator
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import com.example.androiddevchallenge.CountDownViewModel
import com.example.androiddevchallenge.statu.*

/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/12 2:53 下午
 */
class CountTimer(var viewModel: CountDownViewModel) {
    private var valueAnimator: ValueAnimator? = null
    private fun initAnimator() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(viewModel.totalTime.toInt(), 0).apply {
                interpolator = LinearInterpolator()
                duration = viewModel.totalTime * 1000L
                addUpdateListener {
                    Log.d("wzg", "倒计时:----" + it.animatedValue)
                    viewModel.remainingTime = (it.animatedValue as Int).toLong()
                }
                addListener {
                    doOnEnd {
                        complete()
                    }
                }
            }
        } else {
            valueAnimator?.cancel()
            valueAnimator?.setIntValues(viewModel.totalTime.toInt(), 0)
            valueAnimator?.duration = viewModel.totalTime * 1000L
            Log.d("wzg", "重新设置值:----" + viewModel.totalTime.toInt())
        }
    }

    fun start() {
        initAnimator()
        valueAnimator?.start()
        viewModel.statu = StartStatu(viewModel)
    }


    fun pause() {
        valueAnimator?.pause()
        viewModel.statu = PauseStatu(viewModel)
    }

    fun resume() {
        valueAnimator?.resume()
        viewModel.statu = StartStatu(viewModel)
    }

    fun stop() {
        valueAnimator?.cancel()
        viewModel.resetTime(0)
        viewModel.statu = InitStatu(viewModel)
    }

    fun complete() {
        viewModel.remainingTime = 0
        viewModel.statu = CompleStatu(viewModel)
    }

    fun reset() {
        initAnimator()
        viewModel.statu = ReadyStatu(viewModel)
        viewModel.resetTime(viewModel.totalTime)
    }

    fun ready(totalTime: Long) {
        viewModel.statu = ReadyStatu(viewModel)
        viewModel.resetTime(totalTime)
    }

}