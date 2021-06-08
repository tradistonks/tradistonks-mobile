package com.tradistonks.app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tradistonks.app.navigation.Navigation.Page
import com.tradistonks.app.navigation.Navigation.Page.*
import com.tradistonks.app.utilities.Events

class ViewModelLogin : ViewModel() {
    private val _navigateTo = MutableLiveData<Events<Page>>()
    val navigateTo: LiveData<Events<Page>> = _navigateTo

    fun home() {
        _navigateTo.value = Events(Home)
    }

    fun login() {
        _navigateTo.value = Events(Login)
    }
}