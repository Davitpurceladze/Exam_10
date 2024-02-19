package com.example.exam_10.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_10.data.common.Resource
import com.example.exam_10.domain.usecase.story.GetStoryUseCase
import com.example.exam_10.presentation.event.home.HomeEvents
import com.example.exam_10.presentation.mapper.story.toPresenter
import com.example.exam_10.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoryUseCase: GetStoryUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()


    fun onEvent(event: HomeEvents){
        when(event){
            is HomeEvents.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            getStoryUseCase().collect {
                when (it) {
                    is Resource.Loading -> _homeState.update { currentState ->
                        currentState.copy(
                            isLoading = it.loading
                        )
                    }

                    is Resource.Error -> _homeState.update { currentState ->
                        currentState.copy(
                            errorMessage = it.errorMessage
                        )
                    }

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                 stories = it.data.map {
                                     it.toPresenter()
                                 }
                            )
                        }
                    }
                }
            }
        }
    }

}