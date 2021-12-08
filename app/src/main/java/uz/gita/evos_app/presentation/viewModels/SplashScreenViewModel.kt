package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openMainLiveData : LiveData<Unit>
    val openLoginLiveData : LiveData<Unit>
}