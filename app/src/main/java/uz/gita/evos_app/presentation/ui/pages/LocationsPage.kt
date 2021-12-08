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
import uz.gita.evos_app.data.models.LocationData
import uz.gita.evos_app.databinding.PageLocationBinding
import uz.gita.evos_app.presentation.ui.adapters.LocationAdapter
import uz.gita.evos_app.presentation.ui.screens.MainScreenDirections
import uz.gita.evos_app.presentation.viewModels.LocationPageViewModel
import uz.gita.evos_app.presentation.viewModels.impl.LocationPageViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class LocationsPage : Fragment(R.layout.page_location) {

    private val binding by viewBinding(PageLocationBinding::bind)
    private val viewModel: LocationPageViewModel by viewModels<LocationPageViewModelImpl>()
    private val locationAdapter: LocationAdapter by lazy { LocationAdapter() }
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        rvLoc.adapter = locationAdapter
        rvLoc.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        requireArguments()?.let {
            viewModel.getLocationsByType(it.getInt("type", 1).toLong())
        }
        locationAdapter.setClickListener {
            navController.navigate(MainScreenDirections.actionMainScreenToOpenMapScreen(it))
        }

        viewModel.locationsLiveData.observe(viewLifecycleOwner, observeLocations)
    }

    private val observeLocations = Observer<List<LocationData>> {
        locationAdapter.submitList(it)
        locationAdapter.notifyDataSetChanged()
    }

}