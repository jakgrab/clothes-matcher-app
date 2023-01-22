package com.example.clothesmatcher.screens.options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesmatcher.constants.Constants
import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.repository.UrlRepository
import com.example.clothesmatcher.room.UrlEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class OptionsViewModel @Inject constructor(private val urlRepository: UrlRepository) : ViewModel() {

    private val _urlList = MutableStateFlow<List<UrlEntity>>(emptyList())
    val urlList = _urlList.asStateFlow()

    private val _defaultUrl = MutableStateFlow<String>(BASE_URL)
    val defaultUrl = _defaultUrl.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _defaultUrl.value = urlRepository.getDefault()

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
            urlRepository.updateUrl(url)
        }
    }

    fun addUrl(url: String) {
        val urlEntity = UrlEntity(url, 1)
        viewModelScope.launch(Dispatchers.Default) {
            urlRepository.addUrl(urlEntity)
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