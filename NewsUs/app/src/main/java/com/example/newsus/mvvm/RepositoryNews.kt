package com.example.newsus.mvvm

import com.example.newsus.api.API_service

class RepositoryNews constructor(private val retrofitService: API_service) {

    fun getAllNewsRepository() = retrofitService.getDataFromAPI()
}