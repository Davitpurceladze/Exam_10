package com.example.exam_10.domain.repository.story

import com.example.exam_10.data.common.Resource
import com.example.exam_10.domain.model.story.GetStory
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    suspend fun fetchStories() : Flow<Resource<List<GetStory>>>
}