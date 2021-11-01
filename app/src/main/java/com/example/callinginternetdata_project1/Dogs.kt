package com.example.callinginternetdata_project1

import com.squareup.moshi.Json

data class Dogs ( //Data class 'Dogs' contains the Api response key= message and value type= String in Json Annotation format.
    @Json(name = "message")
    val message: String?,

  //  @Json(name = "success")
    //val status: String?,


    )

