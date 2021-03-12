package com.example.androiddevchallenge.statu

import com.example.androiddevchallenge.CountDownViewModel
import com.example.androiddevchallenge.R

/**
 * describe 未开始状态.
 *
 * @author wangzhangang
 * @date 2021/3/12 3:19 下午
 */
class ReadyStatu(var viewModel: CountDownViewModel) : IStatu {
    override fun getProgress(): Float = 1f

    override fun startButtonResId(): Int = R.drawable.ic_start

    override fun startButtonEnabled() = true

    override fun resetButtonEnabled() = true

    override fun stopButtonEnabled() = true

    override fun cuntDownViewShow() = true

    override fun setTimeButtonShow() = false

    override fun clickStarButton() =viewModel.timer.start()

    override fun clickResetButton() {
    }

    override fun clickStopButton() = viewModel.timer.stop()
}