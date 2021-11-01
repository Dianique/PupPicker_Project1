package com.example.callinginternetdata_project1.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callinginternetdata_project1.Dogs
import kotlinx.coroutines.launch

class DogViewModel :
    ViewModel() { //Creates a ViewModel class; responsible for saving data in state and acts as an Observable.

    private val _dogImages =
        MutableLiveData<Dogs>() //Create a private val that's only changeable and viewable by me OR rather, within the class itself
    val dogImages: LiveData<Dogs> =
        _dogImages //Set the private val above to val dogImages, which holds Live Data (a holder/wrapper for data objects Dogs and provides observable before for the ViewModel).

    init {
        getNewDog() //Init gives priority to code within its body to be executed first when class is instantiated
    }

    fun getNewDog() { //Function that's initiated first handles the Coroutine Launch
        viewModelScope.launch {
            _dogImages.value = DogImageApi.retrofitService.getRandomDog()
            //_dogImages sets a value that's retrieved as getRandomDog from the retrofitservice activities.
        }
    }
}