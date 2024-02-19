package com.example.exam_10.domain.usecase.story

import com.example.exam_10.domain.repository.story.StoryRepository
import javax.inject.Inject

class GetStoryUseCase @Inject constructor(
    private val storyRepository: StoryRepository
) {

   suspend operator fun invoke() = storyRepository.fetchStories()
}