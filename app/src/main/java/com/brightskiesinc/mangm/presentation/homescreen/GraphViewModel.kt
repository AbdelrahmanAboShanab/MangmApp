package com.brightskiesinc.mangm.presentation.homescreen

import com.brightskiesinc.mangm.presentation.base.BaseViewModel
import com.github.mikephil.charting.data.Entry
import com.google.android.material.tabs.TabLayout.Tab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

//private val graphRepository: GraphRepository
internal class GraphViewModel() : BaseViewModel() {

    private val _graphComponentsStateFlow = MutableStateFlow<List<GraphUIComponent>?>(null)
    val graphComponentsState = _graphComponentsStateFlow.asStateFlow()

//    fun getGraphComponents() {
//        viewModelScope.launch {
//            showLoading()
//            graphRepository.getGraphComponents()
//                .onError { emitErrorUIMessage(it) }
//                .onSuccess {
//                    hideLoading()
//                    _graphomponentsStateFlow.value = it
//                }
//        }
//    }


}

data class GraphUIComponent(
    val min: Float,
    val avg: Float,
    val max: Float,
    val tabs: Map<Tab, ArrayList<Entry>>,
    val todayValue: String,


    )



