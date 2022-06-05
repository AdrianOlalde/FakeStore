package com.example.fakestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestore.databinding.FragmentSearchBinding


class searchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.BTNSearchButton.setOnClickListener(){4
            val directionS = searchFragmentDirections.actionSearchFragmentToDetailFragment(binding.ETSearchBox.text.toString(), "search")
            findNavController().navigate((directionS))
        }

        return binding.root
    }
}