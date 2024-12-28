// PersonViewModel.kt
package com.example.trabajosis104.ejercicios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {
    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    init {
        _person.value = Person("", "")
    }

    fun updatePerson(name: String, email: String) {
        _person.value = Person(name, email)
    }
}