package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.MenyuPageViewModel
import javax.inject.Inject

@HiltViewModel
class MenyuPageViewModelImpl @Inject constructor(
    private val repository: AppRepository
):MenyuPageViewModel,ViewModel() {
    override val setAdapterLiveData = MutableLiveData<Unit>()
    override val foodsListLiveData = MutableLiveData<List<FoodData>>()

    init {
        foodsListLiveData.postValue(repository.foodsData)
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }
    override fun getAllFood() {
        foodsListLiveData.value = repository.foodsData
    }
}