package com.example.clothesmatcher.screens.options

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.constants.Constants
import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.repository.UrlRepository
import com.example.clothesmatcher.room.UrlEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(private val urlRepository: UrlRepository) : ViewModel() {

    private val _urlList = MutableStateFlow<List<UrlEntity>>(emptyList())
    val urlList = _urlList.asStateFlow()

    private val _defaultUrl = MutableStateFlow(BASE_URL)
    val defaultUrl = _defaultUrl.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _defaultUrl.value = urlRepository.getDefault() ?: BASE_URL

            urlRepository.getAllUrls().collect { allUrls ->
                _urlList.value = allUrls
            }
        }
    }

    fun setToBaseUrl(url: UrlEntity) {
        url.apply {
            this.isDefault = 1
        }
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.setEveryUrlAsNotDefault()
            delay(100)
        }
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.updateUrl(url)
        }
    }

    fun getDefaultUrl() {
        viewModelScope.launch(Dispatchers.Default) {
            _defaultUrl.value = urlRepository.getDefault() ?: BASE_URL
            Log.d("URL", "DEFAULT URL: ${_defaultUrl.value}")
        }
    }

    fun addUrl(url: String) {
        Log.d("URL", "Adding new url: $url")
        val urlEntity = UrlEntity(url, 1)
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.setEveryUrlAsNotDefault()
            Log.d("URL", "EVERY URL NOT DEFAULT")
        }
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.addUrl(urlEntity)
            Log.d("URL", "Added new url: $urlEntity")

        }
    }

    fun setEveryUrlAsNotDefault() {
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.setEveryUrlAsNotDefault()
        }
    }

    fun deleteUrl(url: UrlEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.deleteUrl(url)
        }
    }

}