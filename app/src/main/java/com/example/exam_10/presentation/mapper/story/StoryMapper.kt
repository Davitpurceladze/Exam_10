package com.example.exam_10.presentation.mapper.story

import com.example.exam_10.domain.model.story.GetStory
import com.example.exam_10.presentation.model.story.Story

fun GetStory.toPresenter() = Story(
    cover = cover, id = id, title = title

)