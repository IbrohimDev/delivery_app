package uz.gita.evos_app.presentation.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.evos_app.data.models.LocationEnum
import uz.gita.evos_app.presentation.ui.pages.LocationsPage

class LocationsMainAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
):FragmentStateAdapter(fm,lifecycle){
    override fun getItemCount(): Int = LocationEnum.values().size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("type",position)
        val locationsPage = LocationsPage()
        locationsPage.arguments = bundle
        return locationsPage
    }

}