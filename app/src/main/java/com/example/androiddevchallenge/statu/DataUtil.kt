package com.example.androiddevchallenge.statu

/**
 * describe Java类作用描述.
 *
 * @author wangzhangang
 * @date 2021/3/12 5:10 下午
 */

fun formatTime(time: Long): String {
    var value = time
    val seconds = value % 60
    value /= 60
    val minutes = value % 60
    value /= 60
    val hours = value % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}