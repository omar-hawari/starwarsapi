package com.omarhawari.starwarstrivia.data.remote.dto

class GenericResponse<T>(
    val results: T,
    val count: Int,
    val next: Int,
    val previous: Int,
)