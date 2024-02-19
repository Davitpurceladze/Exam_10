package com.example.exam_10.data.mapper.story

import com.example.exam_10.data.model.story.StoryDto
import com.example.exam_10.domain.model.story.GetStory


fun StoryDto.toDomain() = GetStory(
    cover = cover, id = id, title = title

)