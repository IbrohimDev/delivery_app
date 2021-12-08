package uz.gita.evos_app.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.databinding.ScreenAuthBinding
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class AuthScreen:Fragment(R.layout.screen_auth) {

    private val binding by viewBinding(ScreenAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

    }
}