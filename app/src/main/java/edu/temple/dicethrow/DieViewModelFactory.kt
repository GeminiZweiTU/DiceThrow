package edu.temple.dicethrow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class DieViewModelFactory(private val sides: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(DieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DieViewModel(sides) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
