package br.com.zup.edu.shared.exception

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse

fun StatusRuntimeException.toHttpResponse(): MutableHttpResponse<*> {
    return when (status.code) {
        Status.ALREADY_EXISTS.code -> HttpResponse.unprocessableEntity<Any>()
        else -> HttpResponse.serverError<Any>()
    }
}