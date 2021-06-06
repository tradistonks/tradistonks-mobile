package com.tradistonks.app

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.security.InvalidParameterException

class Navigation {

    enum class Page { Home, Welcome, Login, Register }

    fun Fragment.naviguer(from: Page, to: Page) {
        if(from == to) {
            throw InvalidParameterException("Impossible de naviguer à $to")
        }

        when(to){
            Page.Home -> { findNavController().navigate(R.id.fragment_home) }
            Page.Welcome -> { findNavController().navigate(R.id.fragment_welcome) }
            Page.Login -> { findNavController().navigate(R.id.fragment_login) }
            Page.Register -> { findNavController().navigate(R.id.fragment_register) }
        }
    }
}