package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManualSumViewModel : ViewModel() {

    private val model = ManualSumModel()

    private val _details = MutableLiveData<String>()
    val details: LiveData<String> get() = _details

    private val _numA = MutableLiveData<Int>()
    val numA: LiveData<Int> get() = _numA

    private val _numB = MutableLiveData<Int>()
    val numB: LiveData<Int> get() = _numB

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> get() = _result

    fun calculateManualSum(a: Int, b: Int) {
        val (additionDetails, sumResult) = model.sumWithDetails(a, b)
        _details.value = additionDetails
        _numA.value = a
        _numB.value = b
        _result.value = sumResult
    }
}
