package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.statu.IStatu
import com.example.androiddevchallenge.statu.InitStatu


/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/12 10:37 上午
 */
class CountDownViewModel : ViewModel() {
    /**
     * 用户设置的总时长
     */
    var totalTime: Long by mutableStateOf(0)

    /**
     * 剩余的时长
     */
    var remainingTime: Long by mutableStateOf(0)

    val timer = CountTimer(this)

    /**
     * 页面的view状态
     */
    var statu: IStatu by mutableStateOf(InitStatu(this))

    fun resetTime(time: Long) {
        if (time == 0L) return
        totalTime = time
        remainingTime = time
    }

}