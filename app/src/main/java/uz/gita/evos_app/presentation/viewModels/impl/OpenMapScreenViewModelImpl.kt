package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.LocationData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.OpenMapScreenViewModel
import javax.inject.Inject

@HiltViewModel
class OpenMapScreenViewModelImpl @Inject constructor(
    private val repository: AppRepository
) :OpenMapScreenViewModel,ViewModel(){
    override val setDataLiveData = MutableLiveData<Pair<List<LocationData>,LocationData>>()

    override fun readyMap(chooseLoc:LocationData) {
        setDataLiveData.value = Pair(repository.locationData,chooseLoc)
    }

}