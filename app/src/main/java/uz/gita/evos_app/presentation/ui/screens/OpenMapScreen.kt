package uz.gita.evos_app.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.data.models.LocationData
import uz.gita.evos_app.databinding.ScreenOpenMapBinding
import uz.gita.evos_app.presentation.viewModels.OpenMapScreenViewModel
import uz.gita.evos_app.presentation.viewModels.impl.OpenMapScreenViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class OpenMapScreen : Fragment(R.layout.screen_open_map),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val binding:ScreenOpenMapBinding by viewBinding(ScreenOpenMapBinding::bind)
    private val viewModel:OpenMapScreenViewModel by viewModels<OpenMapScreenViewModelImpl>()
    private val arsgs:OpenMapScreenArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope{

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@OpenMapScreen)

        viewModel.setDataLiveData.observe(viewLifecycleOwner,observeSetData)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        viewModel.readyMap(arsgs.data)

    }

    private val observeSetData = Observer<Pair<List<LocationData>,LocationData>>{

        for (element in it.first) {
            val latLng = LatLng(element.latitude,element.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(element.name)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_loc))
            )
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.second.latitude,it.second.longitude),14f))
    }
}