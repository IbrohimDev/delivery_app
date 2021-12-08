package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.FoodData

interface LikePageViewModel {
    val setAdapterLiveData:LiveData<List<FoodData>>

    fun getAllFavList()
    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)
    fun emptyFavList()
}