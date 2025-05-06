package com.hashinology.dictionarycompose.feature_dictionary.data.remote.dto

import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any?>? = null,
    val definition: String? = null,
    val example: String? = null,
    val synonyms: List<Any?>? = null
){
    fun toDefinition() : Definition{
        return Definition(
            antonyms,
            definition,
            example,
            synonyms
        )
    }
}