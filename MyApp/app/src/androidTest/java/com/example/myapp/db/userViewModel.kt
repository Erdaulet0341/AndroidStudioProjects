package com.example.myapp.db

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class userViewModel(private val repository: userRepository) : ViewModel() {

    val allWords: LiveData<List<User>> = repository.allWords.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}

class userViewModelFactory(private val repository: userRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(userViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return userViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
