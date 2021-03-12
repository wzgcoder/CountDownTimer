package com.example.androiddevchallenge.statu

/**
 * describe 不同状态下页面的状态自己去实现
 *
 * @author wangzhangang
 * @date 2021/3/12 2:18 下午
 */
interface IStatu {
    fun getProgress() : Float

    fun startButtonResId(): Int

    fun startButtonEnabled(): Boolean

    fun resetButtonEnabled(): Boolean

    fun stopButtonEnabled(): Boolean

    fun cuntDownViewShow(): Boolean

    fun setTimeButtonShow(): Boolean

    fun clickStarButton()

    fun clickResetButton()

    fun clickStopButton()
}