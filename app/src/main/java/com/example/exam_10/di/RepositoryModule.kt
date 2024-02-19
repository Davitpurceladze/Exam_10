package com.example.exam_10.di

import com.example.exam_10.data.common.HandleResponse
import com.example.exam_10.data.repository.story.StoryRepositoryImpl
import com.example.exam_10.data.service.story.StoryService
import com.example.exam_10.domain.repository.story.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStoryRepository(
        storyService: StoryService,
        handleResponse: HandleResponse
    ) : StoryRepository {
        return StoryRepositoryImpl(
            storyService = storyService,
            handleResponse = handleResponse
        )
    }
}