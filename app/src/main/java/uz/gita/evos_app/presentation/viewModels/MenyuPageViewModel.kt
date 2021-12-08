package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.FoodData

interface MenyuPageViewModel {
    val setAdapterLiveData:LiveData<Unit>

    val foodsListLiveData:LiveData<List<FoodData>>
    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)
    fun getAllFood()
}