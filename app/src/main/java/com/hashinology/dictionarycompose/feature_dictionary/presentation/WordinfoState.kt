package com.hashinology.dictionarycompose.feature_dictionary.presentation

import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo

data class WordinfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
