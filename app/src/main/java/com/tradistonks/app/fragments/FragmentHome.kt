package com.tradistonks.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.tradistonks.app.R
import com.tradistonks.app.interfaces.InterfaceHome
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme

class FragmentHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            id = R.id.fragment_home
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent { HomePreview() }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun HomePreview() {
        TradistonksAndroidTheme {
            InterfaceHome()
        }
    }
