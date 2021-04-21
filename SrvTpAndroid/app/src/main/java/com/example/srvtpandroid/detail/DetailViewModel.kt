package com.example.srvtpandroid.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.srvtpandroid.network.OrderProperty
import com.example.srvtpandroid.R

class DetailViewModel(orderProperty: OrderProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<OrderProperty>()
    val selectedProperty: LiveData<OrderProperty>
        get() = _selectedProperty
    init {
        _selectedProperty.value = orderProperty
    }

    val displayPropertyPrice: LiveData<String> = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            R.string.display_price)
    }
}