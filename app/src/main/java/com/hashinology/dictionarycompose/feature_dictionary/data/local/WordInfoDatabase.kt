package com.hashinology.dictionarycompose.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hashinology.dictionarycompose.feature_dictionary.data.local.entity.WordInfoEntity
import com.hashinology.dictionarycompose.feature_dictionary.data.util.Converters

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao
}