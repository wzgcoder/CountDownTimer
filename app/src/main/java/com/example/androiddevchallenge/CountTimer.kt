package com.example.androiddevchallenge

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.example.androiddevchallenge.statu.*
import android.media.RingtoneManager

import android.media.Ringtone
import android.net.Uri
import androidx.lifecycle.viewModelScope


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
                    viewModel.remainingTime = (it.animatedValue as Int).toLong()
                }

                doOnEnd {
                    complete()
                }

            }
        } else {
            valueAnimator?.cancel()
            valueAnimator?.setIntValues(viewModel.totalTime.toInt(), 0)
            valueAnimator?.duration = viewModel.totalTime * 1000L
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