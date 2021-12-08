package uz.gita.evos_app.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.evos_app.R
import uz.gita.evos_app.databinding.ItemAdBinding
import uz.gita.evos_app.utils.scope

class AdPage(private val image: String) : Fragment(R.layout.item_ad) {
    private val binding by viewBinding(ItemAdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope{
        Glide.with(requireContext())
            .load(image)
            .into(imageViewAd)
    }
}