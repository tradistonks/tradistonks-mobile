package com.tradistonks.app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tradistonks.app.Navigation
import com.tradistonks.app.Navigation.Page.Register
import com.tradistonks.app.Navigation.Page.Home
import com.tradistonks.app.utilities.Events

class ViewModelRegister : ViewModel() {
    private val _navigateTo = MutableLiveData<Events<Navigation.Page>>()
    val navigateTo: LiveData<Events<Navigation.Page>> = _navigateTo

    fun accueil() {
        _navigateTo.value = Events(Home)
    }

    fun register(){
        _navigateTo.value = Events(Register)
    }
}