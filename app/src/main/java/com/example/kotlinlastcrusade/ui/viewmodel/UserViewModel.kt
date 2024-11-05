package com.example.kotlinlastcrusade.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinlastcrusade.domain.model.Repo
import com.example.kotlinlastcrusade.domain.model.User
import com.example.kotlinlastcrusade.domain.usecase.GetUserDetailsUseCase
import com.example.kotlinlastcrusade.domain.usecase.GetUserReposUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {
    private val _allDataReceived = MutableLiveData<Pair<User, List<Repo>>>()
    val allDataReceived: LiveData<Pair<User, List<Repo>>> = _allDataReceived

    fun getAllData(
        username: String,
    ) {
        viewModelScope.launch {
            val userDetails = getUserDetailsUseCase(username)
            val userRepos = getUserReposUseCase(username)

            _allDataReceived.postValue(
                Pair(
                    userDetails,
                    userRepos
                ),
            )
        }
    }
}