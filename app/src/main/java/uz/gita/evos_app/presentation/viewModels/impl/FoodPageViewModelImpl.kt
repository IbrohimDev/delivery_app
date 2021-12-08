package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.presentation.viewModels.FoodPageViewModel
import javax.inject.Inject


@HiltViewModel
class FoodPageViewModelImpl @Inject constructor():FoodPageViewModel,ViewModel() {
    override val foodAdapterLiveData = MutableLiveData<Unit>()
    init {
        foodAdapterLiveData.value = Unit
    }


}