package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.LocationData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.LocationPageViewModel
import javax.inject.Inject

@HiltViewModel
class LocationPageViewModelImpl @Inject constructor(
    private val repository: AppRepository
):LocationPageViewModel,ViewModel() {
    override val locationsLiveData = MutableLiveData<List<LocationData>>()
    override fun getLocationsByType(type: Long) {
        locationsLiveData.value = repository.locationsByType(type+1)
    }
}