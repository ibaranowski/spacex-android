package com.spacex.rockets.data.exceptions

import com.spacex.rockets.data.utils.EMPTY
import com.google.gson.annotations.SerializedName

data class RestException(

        @SerializedName("status")
        val status: String? = EMPTY,

        @SerializedName("message")
        override val message: String? = EMPTY,

        @SerializedName("error_code")
        val errorCode: String? = EMPTY) : Throwable()