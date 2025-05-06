package com.hashinology.dictionarycompose.feature_dictionary.domain.repository

import com.hashinology.dictionarycompose.core.Resource
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}