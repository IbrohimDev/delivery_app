package uz.gita.evos_app.presentation.ui.pages

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
import uz.gita.evos_app.databinding.PageLikeBinding
import uz.gita.evos_app.presentation.ui.adapters.FavouriteAdapter
import uz.gita.evos_app.presentation.ui.screens.MainScreenDirections
import uz.gita.evos_app.presentation.viewModels.LikePageViewModel
import uz.gita.evos_app.presentation.viewModels.impl.LikePageViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class LikePage : Fragment(R.layout.page_like) {

    private val binding by viewBinding(PageLikeBinding::bind)
    private val viewModel: LikePageViewModel by viewModels<LikePageViewModelImpl>()
    private var _adapter: FavouriteAdapter? = null
    private val adapter: FavouriteAdapter get() = _adapter!!
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


        tvDeleteList.setOnClickListener {
            viewModel.emptyFavList()
        }
        viewModel.setAdapterLiveData.observe(viewLifecycleOwner, observeSetAdapter)
    }

    private val observeSetAdapter = Observer<List<FoodData>> {
        _adapter = FavouriteAdapter()
        _adapter?.submitList(it)

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

        binding.rvList.adapter = adapter
        binding.rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _adapter = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavList()
    }
}