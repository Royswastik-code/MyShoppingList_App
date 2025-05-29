package com.example.myshoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class LocationViewModel:ViewModel() {
    private var _location= mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address:State<List<GeocodingResult>> = _address

    fun updateLocation(newLocation:LocationData){
        _location.value=newLocation
    }

    fun fetchAddress(latlng:String){
        try{
            viewModelScope.launch {
                val result=RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    "AIzaSyBkA9tidfztxDjo0Z_MMrYqVJwFgMAML34"
                )
                _address.value=result.results
            }
        }catch (e:Exception){
            Log.d("res1","${e.cause} ${e.message}")
        }
    }
}