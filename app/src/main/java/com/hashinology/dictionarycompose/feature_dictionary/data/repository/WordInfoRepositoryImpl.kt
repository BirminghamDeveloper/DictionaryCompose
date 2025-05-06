package com.hashinology.dictionarycompose.feature_dictionary.data.repository

import com.hashinology.dictionarycompose.core.Resource
import com.hashinology.dictionarycompose.feature_dictionary.data.local.WordInfoDao
import com.hashinology.dictionarycompose.feature_dictionary.data.remote.DictionaryApi
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo
import com.hashinology.dictionarycompose.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWord(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException){
            emit(Resource.Error(
                message = "Oops, Something went Wrong!",
                data = wordInfos
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Cehck your Internet Connection!",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }

}