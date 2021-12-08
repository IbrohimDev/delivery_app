package uz.gita.evos_app.presentation.ui.screens

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.databinding.ScreenMainBinding
import uz.gita.evos_app.presentation.ui.adapters.MainAdapter
import uz.gita.evos_app.presentation.ui.pages.MainPage
import uz.gita.evos_app.presentation.viewModels.MainScreenViewModel
import uz.gita.evos_app.presentation.viewModels.impl.MainScreenViewModelImpl
import uz.gita.evos_app.utils.scope


@AndroidEntryPoint
class MainScreen:Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel:MainScreenViewModel by viewModels<MainScreenViewModelImpl>()


    private var _adapter:MainAdapter? = null
    private val adapter:MainAdapter get() = _adapter!!
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope{



      bottomViews.setOnNavigationItemSelectedListener { item->
          when(item.itemId){
              R.id.main_menu -> {
                  pager.setCurrentItem(0,true)
              }
              R.id.like_menu -> {
                  pager.setCurrentItem(1,true)

              }
              R.id.menyu_menu -> {
                  pager.setCurrentItem(2,true)

              }
              R.id.restoranlar_menu -> {
                  pager.setCurrentItem(3,true)

              }
              R.id.profile_menu -> {
                  pager.setCurrentItem(4,true)

              }
          }

          return@setOnNavigationItemSelectedListener true
      }
        buttonCart.setOnClickListener {
          navController.navigate(MainScreenDirections.actionMainScreenToOrderScreen())
        }
        viewModel.setAdapterPageLiveData.observe(viewLifecycleOwner,observeSetAdapterPage)
    }
    private val observeSetAdapterPage = Observer<Unit>{
        _adapter = MainAdapter(childFragmentManager,lifecycle)
        binding.pager.adapter = adapter
        binding.pager.isUserInputEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
    _adapter = null
    }


}