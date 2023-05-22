package com.example.patronuschellange.common.presentation.model.mapper

interface ModelMapper<I, O> {
  fun mapToModel(input: I): O
}
