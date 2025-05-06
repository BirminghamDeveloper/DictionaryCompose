package com.hashinology.dictionarycompose.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.License
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Meaning
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Phonetic
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey
    val id : Int = 0,
    val word : String,
    val phonetic : String,
    val license: License,
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val sourceUrls: List<String>,
){
    fun toWordInfo() : WordInfo{
        return WordInfo(license, phonetic, meanings, phonetics, sourceUrls, word)
    }
}