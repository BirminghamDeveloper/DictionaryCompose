package com.hashinology.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashinology.dictionarycompose.feature_dictionary.domain.model.WordInfo
import com.hashinology.dictionarycompose.feature_dictionary.presentation.WordinfoViewModel
import com.hashinology.dictionarycompose.ui.theme.DictionaryComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: WordinfoViewModel = hiltViewModel()
            val state = viewModel.wordInfo.value
            val snackBarHostState = remember { SnackbarHostState() }

            ObserveSnackBar(viewModel,snackBarHostState)

            DictionaryComposeTheme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    }
                ){ innerPadding ->
                    if(viewModel.wordInfo.value.isLoading){
                        Box(Modifier.fillMaxSize()){
                            CircularProgressIndicator(
                                modifier = Modifier.align(Center),
                                color = Color.Blue,
                                strokeWidth = 2.dp
                            )
                        }
                    }
                    Column(Modifier.fillMaxSize().padding(innerPadding)){
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.searchQuery.value,
                            onValueChange = viewModel::onSearch,
                            placeholder = {
                                Text("Search for a word")
                            }
                        )

                        Spacer(Modifier.height(16.dp))

                        LazyColumn(
                            Modifier.fillMaxSize()
                        ){
                            items(viewModel.wordInfo.value.wordInfoItems.size){ index ->
                                val wordInfo = viewModel.wordInfo.value.wordInfoItems[index]
                                if(index > 0){
                                    Spacer(Modifier.height(8.dp))
                                }
                                WordInfoItem(wordInfo = wordInfo)
                                if(index < viewModel.wordInfo.value.wordInfoItems.size - 1){
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WordInfoItem(
    modifier: Modifier = Modifier,
    wordInfo : WordInfo
){
    Column(
        modifier = modifier
    ){
        Text(
            text = wordInfo.word?:"A word",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Bold,
        )
        wordInfo.meanings?.forEach{ meaning->
            Text(text = meaning.partOfSpeech?: "partOfSpeech", fontWeight = FontWeight.Bold)
            meaning.definitions?.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition?.definition}")
                Spacer(Modifier.height(8.dp))
                Text(text = "Example ${ definition?.example}" )
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun ObserveSnackBar(
    viewModel: WordinfoViewModel,
    snackBarHostState : SnackbarHostState,
){
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch {
            viewModel.eventFlow.collectLatest{ event ->
                when(event) {
                    is WordinfoViewModel.UIEvent.ShowSnackBar -> {
                        snackBarHostState.showSnackbar(
                            message = event.message ,
                            actionLabel = "OK",
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            }
        }
    }
}