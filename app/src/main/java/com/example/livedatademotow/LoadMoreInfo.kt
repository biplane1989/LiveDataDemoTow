package com.example.livedatademotow
enum class LoadMoreState{
    LOADING,
    DONE
}
data class LoadMoreInfo(var loadingState:LoadMoreState)