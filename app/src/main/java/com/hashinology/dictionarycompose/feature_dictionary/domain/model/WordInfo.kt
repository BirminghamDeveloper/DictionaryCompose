package com.hashinology.dictionarycompose.feature_dictionary.domain.model



data class WordInfo(
    val license: License? = License(),
    val phonetic : String,
    val meanings: List<Meaning>? = listOf(),
    val phonetics: List<Phonetic>? = listOf(),
    val sourceUrls: List<String>? = listOf(),
    val word: String? = ""
)
