package uz.gita.evos_app.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.databinding.ScreenSplashBinding
import uz.gita.evos_app.presentation.viewModels.SplashScreenViewModel
import uz.gita.evos_app.presentation.viewModels.impl.SplashScreenViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class SplashScreen:Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel:SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private val navController by lazy { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        viewModel.openLoginLiveData.observe(this@SplashScreen,observeOpenLogin)
        viewModel.openMainLiveData.observe(this@SplashScreen,observeOpenMain)


    }
    private val observeOpenMain = Observer<Unit>{
       navController.navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    private val observeOpenLogin = Observer<Unit>{

    }

}