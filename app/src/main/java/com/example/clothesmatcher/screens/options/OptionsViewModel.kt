package com.example.clothesmatcher.screens.options

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.repository.UrlRepository
import com.example.clothesmatcher.room.UrlEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(private val urlRepository: UrlRepository) : ViewModel() {

    private val _urlList = MutableStateFlow<List<UrlEntity>>(emptyList())
    val urlList = _urlList.asStateFlow()

    private val _defaultUrl = MutableStateFlow(BASE_URL)
    val defaultUrl = _defaultUrl.asStateFlow()

    var numResults = 2

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
//        viewModelScope.launch(Dispatchers.Default) {
//            urlRepository.setEveryUrlAsNotDefault()
//        }
        Log.d("URL", "Set as default: $url")
        setAsNotDefault(UrlEntity(_defaultUrl.value))

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

        val newUrl = UrlEntity(url, 1)
//        viewModelScope.launch(Dispatchers.Default) {
//            urlRepository.setEveryUrlAsNotDefault()
//            Log.d("URL", "EVERY URL NOT DEFAULT")
//        }
        setAsNotDefault(UrlEntity(_defaultUrl.value))

        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.addUrl(newUrl)
            Log.d("URL", "Added new url: $newUrl")

        }
    }

    private fun setAsNotDefault(url: UrlEntity) {
        url.apply {
            this.isDefault = 0
        }
        Log.d("URL", "URL $url NO LONGER DEFAULT")
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.updateUrl(url)
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