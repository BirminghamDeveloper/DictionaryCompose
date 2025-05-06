package com.hashinology.dictionarycompose.feature_dictionary.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

import com.hashinology.dictionarycompose.feature_dictionary.domain.model.License
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Meaning
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.Phonetic
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
){
    @TypeConverter
    fun fromMeaningsJson(json: String) : List<Meaning> {
        return jsonParser.fromJson(
            json,
            object: TypeToken<List<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>) : String{
        return jsonParser.toJson(
            meanings,
            object : TypeToken<List<Meaning>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toLicenseJson(license : License) : String {
        return jsonParser.toJson(
            license,
            object : TypeToken<License>(){}.type
        ) ?: "{}"
    }

    @TypeConverter
    fun fromLicenseJson(license : String) : License {
        return jsonParser.fromJson(
            license,
            object : TypeToken<License>(){}.type
        ) ?: License()
    }

    @TypeConverter
    fun fromPhoneticJson(json: String) : List<Phonetic> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<List<Phonetic>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toPhoneticJson(phonetics: List<Phonetic>) : String{
        return jsonParser.toJson(
            phonetics,
            object : TypeToken<List<Phonetic>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromSourceUrlsJson(json: String) : List<String> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<List<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toSourceUrlsJson(sourceUrls: List<String>) : String{
        return jsonParser.toJson(
            sourceUrls,
            object : TypeToken<List<String>>(){}.type
        ) ?: "[]"
    }
}
