package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.evos_app.data.models.AdsData
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.MainPageViewModel
import javax.inject.Inject

@HiltViewModel
class MainPageViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : MainPageViewModel, ViewModel() {
    override val adsListLiveData = MutableLiveData<List<AdsData>>()
    override val foodsListLiveData = MutableLiveData<List<FoodData>>()
    override val nextPageLiveData = MutableLiveData<Unit>()
    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }

    override fun getAllFood() {
        foodsListLiveData.value = repository.foodsData
    }

    private var index = 0

    init {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            adsListLiveData.postValue(repository.adsData)
            foodsListLiveData.postValue(repository.foodsData)

            while(true){
                delay(2000)
                 nextPageLiveData.postValue(Unit)
            }

        }

    }


}