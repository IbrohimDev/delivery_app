package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.LikePageViewModel
import javax.inject.Inject

@HiltViewModel
class LikePageViewModelImpl @Inject constructor(
  private val repository:AppRepository
):LikePageViewModel,ViewModel() {
    override val setAdapterLiveData = MutableLiveData<List<FoodData>>()
    override fun getAllFavList() {
        setAdapterLiveData.value = repository.favouriteList
    }

    init {
        setAdapterLiveData.value = repository.favouriteList
    }

    override fun increaseCount(id: Long) {
        repository.increaseCount(id)
    }

    override fun decreaseCount(id: Long) {
        repository.decreaseCount(id)
    }

    override fun emptyFavList() {
        repository.emptyFav()
        getAllFavList()
    }

}