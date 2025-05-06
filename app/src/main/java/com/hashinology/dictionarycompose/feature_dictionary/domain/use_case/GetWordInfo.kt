package com.hashinology.dictionarycompose.feature_dictionary.domain.use_case

import com.hashinology.dictionarycompose.core.Resource
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo
import com.hashinology.dictionarycompose.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repo: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if (word.isBlank()){
            return flow{ }
        }
        return repo.getWordInfo(word)
    }
}