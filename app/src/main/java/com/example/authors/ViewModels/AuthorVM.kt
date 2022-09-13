package com.example.topalbums.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authors.MainActivity
import com.example.delivery.Request.Items.AuthorItem
import com.example.topalbums.RequestHandler.RequestHandler

class AuthorVM(): ViewModel(), MainActivity.ViewModelCallbacks {

    var isActive = true
    val authorItems: MutableLiveData<List<AuthorItem>> by lazy {
        MutableLiveData<List<AuthorItem>>()
    }

    init {
        requestAlbums()
    }

    fun requestAlbums() {
        RequestHandler.makeRequest(object: RequestHandler.RequestCallback {
            override fun onResult(result: List<AuthorItem>?) {
                if(result != null) {
                    authorItems.value = result
                    if(isActive) authorItems.postValue(result)
                }
            }
        })
    }

    fun getVMCallback(): MainActivity.ViewModelCallbacks {
        return this
    }

    override fun isActive(activityActive: Boolean) {
        isActive = activityActive
    }

    override fun retryRequest() {
       requestAlbums()
    }
}