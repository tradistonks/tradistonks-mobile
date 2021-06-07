package com.tradistonks.app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tradistonks.app.Navigation.Page
import com.tradistonks.app.Navigation.Page.Register
import com.tradistonks.app.Navigation.Page.Login
import com.tradistonks.app.utilities.Events

class ViewModelWelcome : ViewModel() {
    private val _navigateTo = MutableLiveData<Events<Page>>()
    val navigateTo: LiveData<Events<Page>> = _navigateTo

    fun login() {
        _navigateTo.value = Events(Login)
    }

    fun register() {
        _navigateTo.value = Events(Register)
    }
}