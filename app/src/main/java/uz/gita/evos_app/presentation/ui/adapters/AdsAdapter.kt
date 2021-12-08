package uz.gita.evos_app.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.evos_app.presentation.ui.pages.AdPage

class AdsAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val adPage: AdPage,
    private val count: Int
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment = adPage
}