package com.sahrulhidayat.capstone.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sahrulhidayat.capstone.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _fragmentDetailsBinding: FragmentDetailsBinding? = null
    private val binding get() = _fragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDetailsBinding = null
    }
}