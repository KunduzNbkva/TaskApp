package com.example.taskapp.ui.profile


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.extentions.loadImage
import com.example.taskapp.extentions.showToast
import com.example.taskapp.utils.Preferences

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val selectImageFromGalleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent()){
         it?.let {
             binding.imgProfile.loadImage(it)
             showToast("Success")

             Preferences(requireContext()).imgProfile = it.toString()
         }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
       binding.cardView.setOnClickListener {
             selectImageFromGalleryResult.launch("image/*")
        }

        binding.etProfile.doAfterTextChanged {
            Preferences(requireContext()).profileName = binding.etProfile.text.toString()
        }
    }


    private fun initViews() {
       if (Preferences(requireContext()).profileName != null){
           binding.etProfile.setText(Preferences(requireContext()).profileName.toString())}

        if (Preferences(requireContext()).imgProfile != null)
            binding.imgProfile.loadImage(Uri.parse(Preferences(requireContext()).imgProfile))

    }


}