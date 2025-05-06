package com.hashinology.dictionarycompose.feature_dictionary.data.remote.dto

import com.hashinology.dictionarycompose.feature_dictionary.domain.model.License

data class LicenseDto(
    val name: String? = null,
    val url: String? = null
){
    fun toLicense() : License {
        return License(name, url)
    }
}