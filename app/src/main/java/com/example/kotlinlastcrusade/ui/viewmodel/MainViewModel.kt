package com.example.kotlinlastcrusade.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch {
            val response = getUsersUseCase()
            _users.postValue(response)
        }
    }
}