package com.tradistonks.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.tradistonks.app.R
import com.tradistonks.app.interfaces.InterfaceWelcome
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme

class FragmentWelcome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            id = R.id.fragment_welcome
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent { WelcomePreview() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    TradistonksAndroidTheme {
        InterfaceWelcome()
    }
}