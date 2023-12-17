package com.brightskiesinc.mangm.presentation.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.brightskiesinc.mangm.presentation.base.BaseFragment
import com.brightskiesinc.mangm.utils.extensions.applyDefaultStyle
import com.brightskiesinc.mangmapp.databinding.FragmentGraphBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.tabs.TabLayout

class GraphFragment : BaseFragment<FragmentGraphBinding>() {

    private val vmGraph: GraphViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        vmGraph.getGraphComponents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = getView(FragmentGraphBinding.inflate(inflater, container, false))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observeViewModelState()
//        setUpLineChart()
        setLineChart(week1())
        setTodaysValues()
        setUpTabs()
    }

    //    private fun observeViewModelState() {
//        viewLifecycleOwner.lifecycleScope.launch {
//
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//
//                launch {
//                    vmGraph.graphComponentsState.collect {
//                        it?.let { renderHomeUIComponents(it) }
//                    }
//                }
//            }
//        }
//    }
    private val dataForTab3 = "Data for Tab 3"
    private fun setUpTabs() {

        val tabLayout: TabLayout = binding.tabLayout
        // Add tabs to the TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("1D"))
        tabLayout.addTab(tabLayout.newTab().setText("1W"))
        tabLayout.addTab(tabLayout.newTab().setText("1M"))
        tabLayout.addTab(tabLayout.newTab().setText("3M"))
        tabLayout.addTab(tabLayout.newTab().setText("6M"))
        tabLayout.addTab(tabLayout.newTab().setText("1Y"))


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    // Handle tab selection
                    val selectedTabPosition = it.position
                    updateDataBasedOnTab(selectedTabPosition)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun updateDataBasedOnTab(tabPosition: Int) {
        when (tabPosition) {
            0 -> setLineChart(week1())
            1 -> setLineChart(day1())
            2 -> dataForTab3
            else -> ""
        }
    }

    private fun setTodaysValues() {
        //  binding.todayValue.text= vm.setTodaysValues()
    }

    private fun day1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 3000f))
        sales.add(Entry(1f, 2985f))
        sales.add(Entry(2f, 2890f))
        sales.add(Entry(3f, 2705f))
        sales.add(Entry(4f, 2880f))
        return sales
    } //only for ui testing

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
    private fun setLineChart(entryList: ArrayList<Entry>) {
        val lineChart = binding.lineChart

        val floatList: List<Float> = entryList.map { it.y }
        val min = floatList.min()
        val max = floatList.max()
        val avg = floatList.average().toFloat()

        lineChart.apply {
            binding.todayValue.text= entryList.last().y.toString()
            axisLeft.removeAllLimitLines()
            highlightValues(null)
            applyDefaultStyle(min = min, avg = avg, max = max)
            setOnChartValueSelectedListener(BarChartOnChartValueSelectedListener())
        }
        val valuesPlotLine = LineDataSet(entryList, "")
        valuesPlotLine.applyDefaultStyle(requireContext())

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(valuesPlotLine)
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    inner class BarChartOnChartValueSelectedListener : OnChartValueSelectedListener {
        override fun onValueSelected(e: Entry?, h: Highlight?) {
            binding.todayValue.text = e?.y.toString()
//            binding.xAxisValue.text=e?.x.toString() //instead of "today" assuming we get the dates in x axis
        }

        override fun onNothingSelected() {
        }
    }


}

