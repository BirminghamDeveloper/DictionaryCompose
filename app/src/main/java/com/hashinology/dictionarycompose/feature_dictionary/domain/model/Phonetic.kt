package com.hashinology.dictionarycompose.feature_dictionary.domain.model

data class Phonetic(
    val audio: String? = "",
    val license: License? = null,
    val sourceUrl: String? = "",
    val text: String? = ""
)
