package uz.gita.evos_app.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.LocationEnum
import uz.gita.evos_app.databinding.PageFoodsBinding
import uz.gita.evos_app.presentation.ui.adapters.LocationsMainAdapter
import uz.gita.evos_app.presentation.viewModels.FoodPageViewModel
import uz.gita.evos_app.presentation.viewModels.impl.FoodPageViewModelImpl
import uz.gita.evos_app.utils.scope


@AndroidEntryPoint
class FoodsPage:Fragment(R.layout.page_foods) {

    private val binding by viewBinding(PageFoodsBinding::bind)
    private val viewModel:FoodPageViewModel by viewModels<FoodPageViewModelImpl>()

  private var _locationMainAdapter:LocationsMainAdapter? = null
  private val locationMainAdapter:LocationsMainAdapter get() = _locationMainAdapter!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {



        viewModel.foodAdapterLiveData.observe(viewLifecycleOwner,observeFoodAdapter)
    }
    private val observeFoodAdapter = Observer<Unit>{
        _locationMainAdapter = LocationsMainAdapter(childFragmentManager,lifecycle)
        binding.pagerLocations.adapter = locationMainAdapter

        TabLayoutMediator( binding.tabLocations, binding.pagerLocations
        ) { tab, position ->

            val list =  LocationEnum.values()
            list.forEach {
                if(it.pos == position+1){
                    tab.text = it.name
                }
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _locationMainAdapter = null
    }
}