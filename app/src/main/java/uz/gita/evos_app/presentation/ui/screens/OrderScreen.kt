package uz.gita.evos_app.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.databinding.ScreenOrderBinding
import uz.gita.evos_app.presentation.ui.adapters.FavouriteAdapter
import uz.gita.evos_app.presentation.viewModels.OrderScreenViewModel
import uz.gita.evos_app.presentation.viewModels.impl.OrderScreenViewModelImpl
import uz.gita.evos_app.utils.invisible
import uz.gita.evos_app.utils.scope
import uz.gita.evos_app.utils.visible


@AndroidEntryPoint
class OrderScreen : Fragment(R.layout.screen_order) {

    private val binding by viewBinding(ScreenOrderBinding::bind)
    private val viewModel: OrderScreenViewModel by viewModels<OrderScreenViewModelImpl>()
    private val navController by lazy { findNavController() }
    private val adapter: FavouriteAdapter by lazy { FavouriteAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        backBtn.setOnClickListener {
            navController.popBackStack()
        }
        backMenuBtn.setOnClickListener {
            navController.popBackStack()
        }
        viewModel.valuesLiveData.observe(viewLifecycleOwner, observeValues)
        viewModel.sumOrderLiveData.observe(viewLifecycleOwner,observeSum)
    }
    private val observeSum = Observer<Long>{
        binding.apply {
            orderCount.text = "$it so'm"
            generalCount.text = "${it + 9000} so'm"
        }
    }

    private val observeValues = Observer<List<FoodData>> {
        if (it.isNotEmpty()) {
            binding.sectionEmpty.invisible()
            binding.sectionOrder.visible()
            adapter.submitList(it)
            binding.rvBasket.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            binding.rvBasket.adapter = adapter




            adapter.setDecreaseItemClickListener {
             viewModel.decreaseCount(it)
                adapter.notifyDataSetChanged()
            }
            adapter.setIncreaseItemClickListener {
             viewModel.increaseCount(it)
                adapter.notifyDataSetChanged()
            }
            adapter.setItemClickListener {
               navController.navigate(OrderScreenDirections.actionOrderScreenToOpenFoodScreen(it))
            }
        }else{
            binding.sectionEmpty.visible()
            binding.sectionOrder.invisible()
        }

    }
}