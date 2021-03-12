package com.example.androiddevchallenge.statu

import com.example.androiddevchallenge.CountDownViewModel
import com.example.androiddevchallenge.R

/**
 * describe 未开始状态.
 *
 * @author wangzhangang
 * @date 2021/3/12 3:19 下午
 */
class StartStatu(var viewModel: CountDownViewModel) : IStatu {
    override fun getProgress(): Float = viewModel.remainingTime * 1.00f / viewModel.totalTime

    override fun startButtonResId(): Int = R.drawable.ic_pause
    override fun startButtonEnabled() = false
    override fun resetButtonEnabled() = true

    override fun stopButtonEnabled() = true

    override fun cuntDownViewShow() = true

    override fun setTimeButtonShow() = false

    override fun clickStarButton() = viewModel.timer.pause()

    override fun clickResetButton() = viewModel.timer.reset()

    override fun clickStopButton() = viewModel.timer.stop()
}