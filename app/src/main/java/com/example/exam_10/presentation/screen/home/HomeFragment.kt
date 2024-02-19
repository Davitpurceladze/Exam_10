package com.example.exam_10.presentation.screen.home

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.exam_10.databinding.FragmentHomeBinding
import com.example.exam_10.presentation.base.BaseFragment
import com.example.exam_10.presentation.event.home.HomeEvents
import com.example.exam_10.presentation.screen.bottomSheet.BottomSheetFragment
import com.example.exam_10.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var bottomSheetFragment: BottomSheetFragment




    override fun bind() {
        val intent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.imgView.setImageURI(it)
        }

        binding.btnAddImg.setOnClickListener {
            intent.launch("Image/*")
        }

            bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, "BSDialogFragment")
//        viewModel.onEvent(HomeEvents.FetchData)

    }

    override fun bindViewActionListeners() {

    }



    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleHomeState(it)
                }
            }
        }
    }

    private fun handleHomeState(state: HomeState) {
        state.stories?.let {
            println(it)
        }
    }
}