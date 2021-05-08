package com.example.android.trackmysleepquality.sleepquality

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import java.lang.IllegalArgumentException

class SleepQualityViewModelFactory(
    private val sleepNightKey: Long,
    private val dataSource: SleepDatabaseDao,private val application: Application
) :
    ViewModelProvider.AndroidViewModelFactory(
        application
    ) {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(sleepNightKey, dataSource,application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}