package uz.gita.evos_app.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.evos_app.presentation.ui.pages.*

class MainAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> MainPage()
           1 -> LikePage()
           2 -> MenyuPage()
           3 -> FoodsPage()
           else -> ProfilePage()
       }
    }
}