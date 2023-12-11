package com.brightskiesinc.mangm.presentation.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.brightskiesinc.mangm.presentation.base.BaseFragment
import com.brightskiesinc.mangmapp.R
import com.brightskiesinc.mangmapp.databinding.FragmentGraphBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
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


    private fun setUpLineChart() {
        with(binding.lineChart) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false
            xAxis.isEnabled = false

            val yAxis: YAxis = binding.lineChart.axisLeft

            yAxis.setDrawGridLines(false)

//            xAxis.setDrawGridLines(false)
//            xAxis.position = XAxis.XAxisPosition.BOTTOM
//            xAxis.granularity = 1F
//            xAxis.valueFormatter = MyAxisFormatter()

            axisRight.isEnabled = false
            extraRightOffset = 30f
        }
    }

    private fun week1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    @SuppressLint("ResourceType")
    private fun setDataToLineChart() {

        val weekOneSales = LineDataSet(week1(), "Week 1")
        weekOneSales.lineWidth = 3f
//        weekOneSales.valueTextSize = 15f
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekOneSales.color = ContextCompat.getColor(requireContext(), R.color.mangm_orange)


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        binding.lineChart.invalidate()
    }


    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        private var items = arrayListOf("1", "2", "3", "4", "5")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
        }
    }
}
