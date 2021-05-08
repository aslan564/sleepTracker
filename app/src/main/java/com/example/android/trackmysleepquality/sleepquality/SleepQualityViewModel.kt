package com.example.android.trackmysleepquality.sleepquality

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.model.SleepNight
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val sleepNightKey: Long = 0L,
    val database: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val _navigateToSleepTracker =  MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int) {
        viewModelScope.launch {
            val tonight = database.get(sleepNightKey) ?: return@launch
            tonight.sleepQuality = quality
            database.update(tonight)
            _navigateToSleepTracker.value = true
        }
    }
}