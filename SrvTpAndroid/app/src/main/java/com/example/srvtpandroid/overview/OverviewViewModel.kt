package com.example.srvtpandroid.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.srvtpandroid.network.DataProperty
import com.example.srvtpandroid.network.OrderApi
import com.example.srvtpandroid.network.OrderProperty
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class OrderApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<OrderApiStatus>()
    // The external immutable LiveData for the request status String
    val status: LiveData<OrderApiStatus>
        get() = _status

    private val _properties = MutableLiveData<Array<OrderProperty>>()
    val properties: LiveData<Array<OrderProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<OrderProperty>()
    val navigateToSelectedProperty: LiveData<OrderProperty>
        get() = _navigateToSelectedProperty

    /**
     * Call getOrderProperties() on init so we can display status immediately.
     */
    init {
        getOrderProperties()
    }

    private  fun getOrderProperties() {
        viewModelScope.launch {
            _status.value = OrderApiStatus.LOADING
            try {
                _status.value = OrderApiStatus.DONE
                _properties.value = OrderApi.retrofitService.getProperties().data.commands
            } catch (t: Throwable) {
                _status.value = OrderApiStatus.ERROR
                _properties.value = null
            }
        }
    }

    fun displayPropertyDetails(orderProperty: OrderProperty) {
        _navigateToSelectedProperty.value = orderProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}