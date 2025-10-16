package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel(private val sides: Int) : ViewModel() {

    private val _currentRoll = MutableLiveData<Int>(Random.nextInt(1, sides + 1))
    val currentRoll: LiveData<Int> = _currentRoll

    fun roll() {
        _currentRoll.value = Random.nextInt(1, sides + 1)
    }

    fun getSides(): Int = sides
}