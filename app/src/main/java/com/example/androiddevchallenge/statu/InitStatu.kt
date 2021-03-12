package com.example.androiddevchallenge.statu

import com.example.androiddevchallenge.CountDownViewModel
import com.example.androiddevchallenge.R

/**
 * describe 未开始状态.
 *
 * @author wangzhangang
 * @date 2021/3/12 3:19 下午
 */
class InitStatu(var timer: CountDownViewModel) : IStatu {
    override fun getProgress(): Float = 0f

    override fun startButtonResId(): Int = R.drawable.ic_start
    override fun startButtonEnabled() = false
    override fun resetButtonEnabled() = false

    override fun stopButtonEnabled() = false

    override fun cuntDownViewShow() = false

    override fun setTimeButtonShow() = true

    override fun clickStarButton() {

    }

    override fun clickResetButton() {
    }

    override fun clickStopButton() {
    }
}