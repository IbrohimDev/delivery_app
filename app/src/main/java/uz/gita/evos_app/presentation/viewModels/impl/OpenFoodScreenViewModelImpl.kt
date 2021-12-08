package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.data.shared.MyPrefs
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.OpenFoodScreenViewModel
import javax.inject.Inject

@HiltViewModel
class OpenFoodScreenViewModelImpl @Inject constructor(
    private val repository: AppRepository,
    private val prefs:MyPrefs
):OpenFoodScreenViewModel,ViewModel() {

    init {

    }

    override val valuesLiveData = MutableLiveData<FoodData>()
    override val countLiveData = MutableLiveData<Long>()

    override fun setValues(foodData: FoodData) {
        valuesLiveData.value = foodData
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
        countLiveData.value = repository.getCountById(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
        countLiveData.value = repository.getCountById(id)
    }

    override fun addFavItem(id: Long) {
        repository.addFavouriteItem(id)
    }

    override fun deleteFavItem(id: Long) {
        repository.deleteFavouriteItem(id)
    }
}