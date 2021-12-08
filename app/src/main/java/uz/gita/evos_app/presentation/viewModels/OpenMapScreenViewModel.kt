package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.LocationData

interface OpenMapScreenViewModel {
    val setDataLiveData:LiveData<Pair<List<LocationData>,LocationData>>

    fun readyMap(chooseLoc:LocationData)
}