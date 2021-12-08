package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.LocationData


interface LocationPageViewModel {

    val locationsLiveData:LiveData<List<LocationData>>

    fun getLocationsByType(type:Long)
}