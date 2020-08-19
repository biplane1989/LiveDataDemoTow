package com.example.livedatademotow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var images: MutableLiveData<ArrayList<Note>> = MutableLiveData()
    private var _images = ArrayList<Note>()
    private val loadMoreInfo = MutableLiveData<LoadMoreInfo>()
    private val _LoadMoreInfo = LoadMoreInfo(LoadMoreState.DONE)

    init {
        images.value = ArrayList()
    }

    fun getData(): MutableLiveData<ArrayList<Note>> {
        for (item in Data.getData()) {
            _images.add(item)
        }
        images.postValue(_images)
        return images
    }

    fun getListSize(): Int {
        return images.value!!.size
    }

    fun setStatus(position: Int) {
        val newImageList = ArrayList<Note>()
        _images.forEach {
            newImageList.add(it)
        }
        val note = newImageList.get(position).copy()
        note.status = false
        newImageList.set(position, note)
        _images = newImageList
        images.postValue(_images)


    }

    fun loadMore(): LiveData<LoadMoreInfo> {
        if (_LoadMoreInfo.loadingState == LoadMoreState.LOADING) {
            return loadMoreInfo
        }
        _LoadMoreInfo.loadingState = LoadMoreState.LOADING
        CoroutineScope(Dispatchers.Default).launch {
            delay(500)
            _LoadMoreInfo.loadingState = LoadMoreState.DONE
            loadMoreInfo.postValue(_LoadMoreInfo)
            for (item in Data.getData()) {
                _images.add(item)
            }
            images.postValue(_images)
        }

        return loadMoreInfo
    }

}