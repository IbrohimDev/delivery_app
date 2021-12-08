package uz.gita.evos_app.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.AdsData
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.databinding.PageMainBinding
import uz.gita.evos_app.presentation.ui.adapters.FoodsAdapter
import uz.gita.evos_app.presentation.ui.screens.MainScreenDirections
import uz.gita.evos_app.presentation.viewModels.MainPageViewModel
import uz.gita.evos_app.presentation.viewModels.impl.MainPageViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {

    private val binding by viewBinding(PageMainBinding::bind)
    private val viewModel: MainPageViewModel by viewModels<MainPageViewModelImpl>()
    private val adapter: FoodsAdapter by lazy { FoodsAdapter() }
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        recyclerViewPopular.adapter = adapter
        recyclerViewPopular.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        adapter.setDecreaseItemClickListener {
           viewModel.decreaseCount(it)
            adapter.notifyDataSetChanged()
        }
        adapter.setIncreaseItemClickListener {
           viewModel.increaseCount(it)
            adapter.notifyDataSetChanged()

        }
        adapter.setItemClickListener {
          navController.navigate(MainScreenDirections.actionMainScreenToOpenFoodScreen(it))
        }

        viewModel.adsListLiveData.observe(viewLifecycleOwner, observeAdsList)
        viewModel.nextPageLiveData.observe(viewLifecycleOwner, observeNextPage)
        viewModel.foodsListLiveData.observe(viewLifecycleOwner, observeFoodsList)
    }

    private val observeFoodsList = Observer<List<FoodData>> {
        adapter.submitList(it)
        adapter.notifyDataSetChanged()
    }
    private val observeAdsList = Observer<List<AdsData>> {
        binding.pager.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>()
        it.forEach { item ->
            list.add(
                CarouselItem(
                    imageUrl = item.imageURL
                )
            )
        }
        binding.pager.setData(list)
    }
    private val observeNextPage = Observer<Unit> {
        binding.pager.next()
    }
    override fun onResume() {
        super.onResume()
        viewModel.getAllFood()
    }
}