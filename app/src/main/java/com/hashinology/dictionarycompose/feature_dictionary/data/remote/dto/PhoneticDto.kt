package com.hashinology.dictionarycompose.feature_dictionary.data.remote.dto

import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Phonetic

data class PhoneticDto(
    val audio: String? = "",
    val license: LicenseDto? = LicenseDto(),
    val sourceUrl: String? = "",
    val text: String? = ""
){
    fun toPhonetic() : Phonetic {
        return Phonetic(audio, license?.toLicense(), sourceUrl, text)
    }
}