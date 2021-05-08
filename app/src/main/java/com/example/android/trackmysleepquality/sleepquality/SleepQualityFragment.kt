package com.example.android.trackmysleepquality.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepQualityBinding
import com.example.android.trackmysleepquality.sleeptracker.SleepTrackerFragmentDirections

class SleepQualityFragment : Fragment() {


    private lateinit var sleepQualityViewModel:SleepQualityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_quality, container, false)

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource=SleepDatabase.getInstance(requireContext()).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource,application)
        sleepQualityViewModel=ViewModelProvider(this@SleepQualityFragment,viewModelFactory).get(SleepQualityViewModel::class.java)


        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner,  Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
