package com.example.androiddevchallenge.statu

import com.example.androiddevchallenge.CountDownViewModel
import com.example.androiddevchallenge.R

/**
 * describe 未开始状态.
 *
 * @author wangzhangang
 * @date 2021/3/12 3:19 下午
 */
class CompleStatu(var viewModel: CountDownViewModel) : IStatu {
    override fun getProgress(): Float  = 0f

    override fun startButtonResId(): Int = R.drawable.ic_start
    override fun startButtonEnabled() = false

    override fun resetButtonEnabled() = false

    override fun stopButtonEnabled() = false

    override fun cuntDownViewShow() = true

    override fun setTimeButtonShow() = false

    override fun clickStarButton() = viewModel.timer.start()

    override fun clickResetButton() = viewModel.timer.reset()

    override fun clickStopButton() = viewModel.timer.stop()
}