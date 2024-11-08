package com.adista.kl_middle.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MvvmViewModel: ViewModel() {

    private val _counter = MutableSharedFlow<Int>()
    val counter = _counter.asSharedFlow()

    suspend fun increase(last: Int){
        _counter.emit(last + 1)
    }

    suspend fun decrease(last: Int) {
        _counter.emit(last - 1)
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return MvvmViewModel() as T
            }
        }
    }
}
