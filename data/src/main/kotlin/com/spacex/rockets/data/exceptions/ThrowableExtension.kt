package com.spacex.rockets.data.exceptions

import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable?.isNetworkError(): Boolean = this is IOException
fun Throwable?.isServerNotAvailableError(): Boolean = this is SocketTimeoutException
fun Throwable?.isRestError(): Boolean = this is RestException