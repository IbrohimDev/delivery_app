package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.FoodData

interface OpenFoodScreenViewModel {
    val valuesLiveData: LiveData<FoodData>
    val countLiveData:LiveData<Long>

    fun setValues(foodData: FoodData)
    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)
    fun addFavItem(id:Long)
    fun deleteFavItem(id:Long)
}