/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.corp.luqman.kotlintraining.lesson6n7.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.corp.luqman.kotlintraining.lesson6n7.database.SleepDatabaseDao
import kotlinx.coroutines.*

class SleepQualityViewModel(
    private val sleepNightKey: Long = 0L,
    val database: SleepDatabaseDao) : ViewModel(){

    private val jobViewModel = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + jobViewModel)

    private val _navigateToSleepTracker = MutableLiveData<Boolean>()
    val navigateToSleepTracker : LiveData<Boolean>
        get() = _navigateToSleepTracker

    fun doneNavigating(){
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(sleepQuality : Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val toNight = database.get(sleepNightKey)
                toNight?.sleepQuality = sleepQuality
                database.update(toNight!!)
            }
            _navigateToSleepTracker.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobViewModel.cancel()
    }

}
