package com.hashinology.dictionarycompose.feature_dictionary.data.remote.dto

import com.hashinology.dictionarycompose.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val license: LicenseDto = LicenseDto(),
    val phonetic : String = "",
    val meanings: List<MeaningDto> = listOf(),
    val phonetics: List<PhoneticDto> = listOf(),
    val sourceUrls: List<String> = listOf(),
    val word: String = ""
){
    fun toWordInfoEntity() : WordInfoEntity {
        return WordInfoEntity(
            word = word,
            license = license.toLicense(),
            phonetic = phonetic,
            meanings = meanings.map { it.toMeaning() },
            phonetics = phonetics.map { it.toPhonetic() },
            sourceUrls = sourceUrls
        )
    }
}