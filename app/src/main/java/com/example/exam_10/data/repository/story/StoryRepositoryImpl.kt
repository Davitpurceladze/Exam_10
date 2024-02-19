package com.example.exam_10.data.repository.story

import com.example.exam_10.data.common.HandleResponse
import com.example.exam_10.data.common.Resource
import com.example.exam_10.data.mapper.base.asResource
import com.example.exam_10.data.mapper.story.toDomain
import com.example.exam_10.data.service.story.StoryService
import com.example.exam_10.domain.model.story.GetStory
import com.example.exam_10.domain.repository.story.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val storyService: StoryService
) : StoryRepository {
    override suspend fun fetchStories(): Flow<Resource<List<GetStory>>> {
        return handleResponse.safeApiCall {
            storyService.getStories()
        }.asResource {
            it.map {
                it.toDomain()
            }
        }
    }
}