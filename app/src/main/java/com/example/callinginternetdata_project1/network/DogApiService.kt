package com.example.callinginternetdata_project1.network

import com.example.callinginternetdata_project1.Dogs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://dog.ceo/api/"
//Add a base URL(Uniformed Resource Locator) to call the network and make API requests from.

private val moshi =
    Moshi.Builder() //Moshi Library parses JSON objects into Kotlin objects after data requests are received.
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit =
    Retrofit.Builder() //Retrofit speaks to the network to "GET" back data requests, requested of the server from the client.
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface DogApiService { //Interfaces serve as custom types that aren't instantiated directly but define forms of behavior for other types to implement
    // https://dog.ceo/api/breeds/image/random
    @GET("breeds/image/random") //GET annotation points to a specific API endpoint
    suspend fun getRandomDog(): Dogs //a function/method that defines getRandomDog as the Dogs data class. The suspend keyword makes this function usable in the coroutine.

}

object DogImageApi { //DogImageApi is a singleton object instantiated once within the class and usable throughout the program;
    // The object serves to initialize the retrofit service that communicates with the network.
    val retrofitService: DogApiService by lazy { retrofit.create(DogApiService::class.java) }
}
// Lazily initialized object property: ensure its initialized at first access
//Initialize retrofit api service here.
//"lazy instantiation" is when object creation is purposely delayed until you actually need
// that object to avoid unnecessary computation or use of other computing resources