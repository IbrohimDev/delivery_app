package uz.gita.evos_app.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.evos_app.R
import uz.gita.evos_app.databinding.PageProfileBinding
import uz.gita.evos_app.utils.scope

@AndroidEntryPoint
class ProfilePage:Fragment(R.layout.page_profile) {

    private val binding by viewBinding(PageProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

    }
}