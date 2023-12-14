package com.brightskiesinc.mangm.utils.extensions

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.brightskiesinc.mangmapp.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.data.LineDataSet

fun LineChart.applyDefaultStyle(min: Float, avg: Float, max: Float) {
    animateX(1200, Easing.EaseInSine)

    //simplify graph
    description.isEnabled = false
    legend.isEnabled = false
    xAxis.isEnabled = false
    axisRight.isEnabled = false
    axisLeft.setDrawGridLines(false)
    axisLeft.setDrawAxisLine(false)
    axisLeft.setDrawLabels(false)

    //disable zoom
    isDragEnabled = false
    isDoubleTapToZoomEnabled = false
    isScaleXEnabled = false
    isScaleYEnabled = false

    //dashed zero line
    val limitLine = LimitLine(avg).apply {
        lineWidth = 1f
        lineColor = R.color.dashLine
        enableDashedLine(10f, 10f, 0f)
        label = avg.toString()
        labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
    }
    axisLeft.addLimitLine(limitLine)

    //max line
    val maxLine = LimitLine(max).apply {
        lineColor = Color.TRANSPARENT
        label = max.toString()
        labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
    }
    axisLeft.addLimitLine(maxLine)

    //min line
    val minLine = LimitLine(min).apply {
        lineColor = Color.TRANSPARENT
        label = min.toString()
        labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
    }
    axisLeft.addLimitLine(minLine)

}

fun LineDataSet.applyDefaultStyle(context: Context) {
    lineWidth = 3f
    mode = LineDataSet.Mode.CUBIC_BEZIER
    setDrawValues(false)
    setDrawCircles(false)
    color = ContextCompat.getColor(context, R.color.orangeMangm)
    highLightColor = Color.GRAY //USE COLORS FILE
    setDrawHorizontalHighlightIndicator(false)
    setDrawFilled(true)
    fillColor = ContextCompat.getColor(context, R.color.orangeMangm)
    fillAlpha = 15
}