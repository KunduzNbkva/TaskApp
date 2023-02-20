package com.example.taskapp.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentOnBoardPageBinding


class OnBoardPageFragment(  var listenerSkip: () -> Unit, var listenerNext: () -> Unit) : Fragment() {
    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
        binding.btnSkip.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
            listenerSkip.invoke()
        }
        binding.btnNext.setOnClickListener {
            listenerNext.invoke()
        }
    }

    private fun initViews() {
        val data = arguments?.getSerializable("onBoard") as BoardModel
        data.img?.let { binding.imgBoard.setImageResource(it) }
        binding.tvTitleBoard.text = data.title
        binding.tvDescBoard.text = data.desc

        binding.btnSkip.isVisible = data.isLast == false
        binding.btnNext.isVisible = data.isLast == false

        binding.btnStart.isVisible = data.isLast == true
    }

}