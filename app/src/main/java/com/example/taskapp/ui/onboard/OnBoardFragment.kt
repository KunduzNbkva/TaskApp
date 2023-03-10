package com.example.taskapp.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardingPageAdapter(childFragmentManager, this::onSkipClick, this::onNextClick)
        binding.vpBoard.adapter = adapter
        binding.dotsIndicator.attachTo(binding.vpBoard)

    }

    private fun onSkipClick() {
        binding.vpBoard.currentItem = 2
    }

    private fun onNextClick() {
        binding.vpBoard.currentItem += 1
    }


}