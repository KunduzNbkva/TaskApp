package com.example.taskapp.ui.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val selectImageFromGalleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent()){
         it?.let {
             binding.imgProfile.setImageURI(it)
         }
    }

  private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
       binding.cardView.setOnClickListener {
             selectImageFromGalleryResult.launch("image/*")
           }
       }


    private fun initViews() {

    }


}