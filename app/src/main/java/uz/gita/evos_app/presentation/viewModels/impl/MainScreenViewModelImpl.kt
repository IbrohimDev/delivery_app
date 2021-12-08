package uz.gita.evos_app.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.evos_app.domain.repository.AppRepository
import uz.gita.evos_app.presentation.viewModels.MainScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(private val repository: AppRepository):
    MainScreenViewModel,ViewModel() {
    override val setAdapterPageLiveData = MutableLiveData<Unit>()

    init {
        setAdapterPageLiveData.value = Unit
    }


}