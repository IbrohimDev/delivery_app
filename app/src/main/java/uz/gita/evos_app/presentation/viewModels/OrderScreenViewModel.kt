package uz.gita.evos_app.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.evos_app.data.models.FoodData

interface OrderScreenViewModel {

    val valuesLiveData:LiveData<List<FoodData>>
    val sumOrderLiveData:LiveData<Long>
    fun setValues()
    fun increaseCount(id:Long)
    fun decreaseCount(id:Long)

}