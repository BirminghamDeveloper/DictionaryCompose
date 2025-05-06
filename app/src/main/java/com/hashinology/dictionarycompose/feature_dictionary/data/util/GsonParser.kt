package com.hashinology.dictionarycompose.feature_dictionary.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(
    private val gSon: Gson
): JsonParser {
    override fun <T> fromJson(json: String, type: Type): T? {
        return gSon.fromJson(json, type)
    }

    override fun <T> toJson(obj: T, type: Type): String? {
        return gSon.toJson(obj, type)
    }
}