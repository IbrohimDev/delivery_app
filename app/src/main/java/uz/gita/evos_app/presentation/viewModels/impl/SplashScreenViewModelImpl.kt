package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.SplashScreenViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
):SplashScreenViewModel,ViewModel() {
    override val openMainLiveData = MutableLiveData<Unit>()

    override val openLoginLiveData = MutableLiveData<Unit>()
    private var count = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getAds()
            appRepository.getAllFoods()
            appRepository.getLocationsByType()
            appRepository.successLoadListener {
                count++
                if(count == 3){
                    openMainLiveData.value = Unit
                }
            }
        }
    }


}