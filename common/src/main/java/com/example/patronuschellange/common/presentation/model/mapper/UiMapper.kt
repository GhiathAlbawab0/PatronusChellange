package com.example.patronuschellange.common.presentation.model.mapper

interface UiMapper<I, O> {
  fun mapToView(input: I): O
}
