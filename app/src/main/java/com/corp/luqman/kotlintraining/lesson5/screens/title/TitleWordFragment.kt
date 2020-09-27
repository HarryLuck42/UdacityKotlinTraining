package com.corp.luqman.kotlintraining.lesson5.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.corp.luqman.kotlintraining.R
import com.corp.luqman.kotlintraining.databinding.FragmentTitleWordBinding


class TitleWordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: FragmentTitleWordBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title_word, container, false)

        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleWordFragmentDirections.actionTitleToGame())
        }
        return binding.root
    }
}