package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.AdsData
import uz.gita.evos_app.data.models.FoodData

interface MainPageViewModel {
    val adsListLiveData:LiveData<List<AdsData>>
    val foodsListLiveData:LiveData<List<FoodData>>
    val nextPageLiveData:LiveData<Unit>

    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)

    fun getAllFood()
}