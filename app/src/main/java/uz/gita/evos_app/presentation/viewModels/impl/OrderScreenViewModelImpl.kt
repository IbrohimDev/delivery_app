package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.OrderScreenViewModel
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModelImpl @Inject constructor(
    private val repository: AppRepository
):OrderScreenViewModel,ViewModel() {
    override val valuesLiveData = MutableLiveData<List<FoodData>>()
    override val sumOrderLiveData = MutableLiveData<Long>()

    init {
       setValues()
    }
    override fun setValues() {
        valuesLiveData.value = repository.orderList
        sumOrderLiveData.value = repository.orderSum
    }
    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
        setValues()
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
        setValues()
    }
}