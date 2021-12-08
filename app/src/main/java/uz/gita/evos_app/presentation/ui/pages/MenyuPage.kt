package uz.gita.evos_app.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.data.models.TypeEnum
import uz.gita.evos_app.databinding.PageMenyuBinding
import uz.gita.evos_app.presentation.ui.adapters.FoodsAdapter
import uz.gita.evos_app.presentation.ui.screens.MainScreenDirections
import uz.gita.evos_app.presentation.viewModels.MenyuPageViewModel
import uz.gita.evos_app.presentation.viewModels.impl.MenyuPageViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class MenyuPage : Fragment(R.layout.page_menyu) {

    private val binding by viewBinding(PageMenyuBinding::bind)
    private val viewModel: MenyuPageViewModel by viewModels<MenyuPageViewModelImpl>()
    private val adapter: FoodsAdapter by lazy { FoodsAdapter() }
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        rvList.adapter = adapter
        rvList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        adapter.setIncreaseItemClickListener {
            viewModel.increaseCount(it)
            adapter.notifyDataSetChanged()
        }
        adapter.setDecreaseItemClickListener {
            viewModel.decreaseCount(it)
            adapter.notifyDataSetChanged()
        }
        adapter.setItemClickListener {
             navController.navigate(MainScreenDirections.actionMainScreenToOpenFoodScreen(it))
        }
        for (value in TypeEnum.values()) {
            tabLayout.addTab(tabLayout.newTab().setText(value.text))
        }



        TabbedListMediator(
            rvList,
            tabLayout,
            getList()
        ).attach()
        viewModel.setAdapterLiveData.observe(viewLifecycleOwner, observeSetAdapter)
        viewModel.foodsListLiveData.observe(viewLifecycleOwner, observeFoodsLiveData)
    }

    private val observeFoodsLiveData = Observer<List<FoodData>> {
        adapter.submitList(it)
        adapter.notifyDataSetChanged()
    }
    private val observeSetAdapter = Observer<Unit> {

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFood()
    }

    private fun getList(): List<Int> {
        val list = ArrayList<Int>()
        for (value in TypeEnum.values()) {
            list.add(value.pos)
        }
        return list
    }

}