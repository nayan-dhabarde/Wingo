package com.nayandhabarde.wingo.model

data class PageResponse<T>(val data: T, val total: Long)