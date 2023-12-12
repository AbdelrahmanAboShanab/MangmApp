package com.brightskiesinc.mangm.presentation.homescreen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.brightskiesinc.mangm.presentation.base.BaseFragment
import com.brightskiesinc.mangmapp.R
import com.brightskiesinc.mangmapp.databinding.FragmentGraphBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class GraphFragment : BaseFragment<FragmentGraphBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = getView(FragmentGraphBinding.inflate(inflater, container, false))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLineChart()
        setDataToLineChart()
    }

    //from vm we need max, min, avg, points, (maybe each point comes with green up or red down and its +- val)

    private fun setUpLineChart() {
        val lineChart = binding.lineChart
        val max = 2890f
        val min = 2870f
        val avg = 2880f
        with(lineChart) {
            animateX(1200, Easing.EaseInSine)
            //simplify graph
            description.isEnabled = false
            legend.isEnabled = false
            xAxis.isEnabled = false
            axisRight.isEnabled = false
            axisLeft.setDrawGridLines(false)
            axisLeft.setDrawAxisLine(false)
            axisLeft.labelCount = 3
            axisLeft.axisMaximum = max
            axisLeft.axisMinimum = min
            axisLeft.spaceTop = 10f
            axisLeft.spaceBottom = 10f

            //dashed zero line
            val limitLine = LimitLine(avg)
            limitLine.lineWidth = 1f
            limitLine.lineColor = Color.LTGRAY
            limitLine.enableDashedLine(10f, 10f, 0f)
            axisLeft.addLimitLine(limitLine)

            //disable zoom
            lineChart.isDragEnabled = false
            lineChart.isDoubleTapToZoomEnabled = false
            lineChart.isScaleXEnabled = false
            lineChart.isScaleYEnabled = false


        }
    }

    private fun week1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 2875f))
        sales.add(Entry(1f, 2870f))
        sales.add(Entry(2f, 2890f))
        sales.add(Entry(3f, 2885f))
        sales.add(Entry(4f, 2880f))
        return sales
    } //only for ui testing

    @SuppressLint("ResourceType")
    private fun setDataToLineChart() {
        val lineChart = binding.lineChart

        val valuesPlotLine = LineDataSet(week1(), "Week 1")
        valuesPlotLine.lineWidth = 3f
        valuesPlotLine.mode = LineDataSet.Mode.CUBIC_BEZIER
        valuesPlotLine.setDrawValues(false)
        valuesPlotLine.setDrawCircles(false)
        valuesPlotLine.color = ContextCompat.getColor(requireContext(), R.color.mangm_orange)
        valuesPlotLine.highLightColor = Color.GRAY
        valuesPlotLine.setDrawHorizontalHighlightIndicator(false)
        valuesPlotLine.setDrawFilled(true)
        valuesPlotLine.fillColor = ContextCompat.getColor(requireContext(), R.color.mangm_orange)
        valuesPlotLine.fillAlpha = 15


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(valuesPlotLine)
        val lineData = LineData(dataSet)
        lineChart.data = lineData
//        lineChart.notifyDataSetChanged() //to be implemented
        lineChart.invalidate()
    }

}
