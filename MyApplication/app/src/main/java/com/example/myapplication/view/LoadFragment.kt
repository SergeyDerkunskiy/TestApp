package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentLoadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadFragment : ViewBindingFragment<FragmentLoadBinding>(FragmentLoadBinding::inflate) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        mainViewModel.getResponse()
    }

    private fun bindViewModel() {
        mainViewModel.remoteDataLD.observe(viewLifecycleOwner){
            mainViewModel.saveRemoteData(it)
            Log.e("AAA", it.home + "\n" + it.link)
            mainViewModel.getLaunchCounter()?.let { counter -> moveToWeb(counter) }
        }
    }

    private fun moveToWeb(counter: Int){
        val action = LoadFragmentDirections.actionLoadFragmentToWebFragment(counter)
        findNavController().navigate(action)
    }
}