// BasicViewModel.kt
package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicViewModel : ViewModel() {
    private val model = BasicModel()

    private val _additions = MutableLiveData<String>()
    val additions: LiveData<String> = _additions

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _numberA = MutableLiveData<String>()
    val numberA: LiveData<String> = _numberA

    private val _numberB = MutableLiveData<String>()
    val numberB: LiveData<String> = _numberB

    fun performAddition(a: Int, b: Int) {
        val (additions, sum, carry) = model.performComplexAddition(a, b)
        _additions.value = additions
        _result.value = sum
        _numberA.value = a.toString()
        _numberB.value = b.toString()
    }
}
