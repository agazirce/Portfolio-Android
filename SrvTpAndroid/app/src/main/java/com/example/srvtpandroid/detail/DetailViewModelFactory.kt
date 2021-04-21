package com.example.srvtpandroid.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.srvtpandroid.network.OrderProperty

class DetailViewModelFactory(
    private val orderProperty: OrderProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(orderProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
