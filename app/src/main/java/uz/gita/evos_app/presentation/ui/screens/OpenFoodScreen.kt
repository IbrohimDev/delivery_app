package uz.gita.evos_app.presentation.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.app.App
import uz.gita.evos_app.data.models.FoodData
import uz.gita.evos_app.databinding.ScreenOpenFoodBinding
import uz.gita.evos_app.presentation.viewModels.OpenFoodScreenViewModel
import uz.gita.evos_app.presentation.viewModels.impl.OpenFoodScreenViewModelImpl
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class OpenFoodScreen : Fragment(R.layout.screen_open_food) {

    private val binding by viewBinding(ScreenOpenFoodBinding::bind)
    private val viewModel: OpenFoodScreenViewModel by viewModels<OpenFoodScreenViewModelImpl>()
    private val args: OpenFoodScreenArgs by navArgs()
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        viewModel.setValues(args.data)

        backImg.setOnClickListener {
            navController.popBackStack()
        }
        addFav.setOnClickListener {
            if (args.data.isSelected) {
                viewModel.deleteFavItem(args.data.id)
                args.data.isSelected = false
                addFav.setTextColor(Color.BLACK)
                addFav.setBackgroundResource((R.drawable.empty_bacl))
                addFav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favourblack, 0, 0, 0)
            } else {
                viewModel.addFavItem(args.data.id)
                args.data.isSelected = true
                addFav.setTextColor(Color.WHITE)
                addFav.setBackgroundResource((R.drawable.fav_back))
                addFav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favourabwhite, 0, 0, 0)
            }
        }
        addItem.setOnClickListener {
          viewModel.increaseCount(args.data.id)

        }
        minusItem.setOnClickListener {
            if(args.data.count > 0)
            viewModel.decreaseCount(args.data.id)

        }

        viewModel.valuesLiveData.observe(viewLifecycleOwner, observeValues)
        viewModel.countLiveData.observe(viewLifecycleOwner,observeCount)
    }
   private val observeCount = Observer<Long>{
       binding.countItem.text = "${it}x"
   }
    private val observeValues = Observer<FoodData> {
        Glide.with(App.instance)
            .load(it.imageURL)
            .into(binding.selectFoodImage)
        binding.apply {
            selectFoodCost.text = "${it.cost} so'm"
            selectFoodName.text = it.name
            countItem.text = "${it.count}x"
            if (it.isSelected) {
                addFav.setTextColor(Color.WHITE)
                addFav.setBackgroundResource((R.drawable.fav_back))
                addFav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favourabwhite, 0, 0, 0)
            } else {
                addFav.setTextColor(Color.BLACK)
                addFav.setBackgroundResource((R.drawable.empty_bacl))
                addFav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favourblack, 0, 0, 0)
            }
        }

    }
}