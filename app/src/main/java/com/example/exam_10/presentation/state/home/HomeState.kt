package com.example.exam_10.presentation.state.home

import com.example.exam_10.presentation.model.story.Story

data class HomeState(

    val stories: List<Story>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)