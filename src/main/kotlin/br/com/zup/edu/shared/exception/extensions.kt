package br.com.zup.edu.shared.exception

import com.google.rpc.BadRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.server.exceptions.response.Error
import java.util.*

fun StatusRuntimeException.toHttpResponse(): MutableHttpResponse<*> {
    return when (status.code) {
        Status.Code.ALREADY_EXISTS -> HttpResponse.unprocessableEntity<Any>()
        Status.Code.NOT_FOUND -> HttpResponse.notFound<Any>()
        Status.Code.FAILED_PRECONDITION -> HttpResponse.badRequest<Any>()
        else -> HttpResponse.serverError<Any>()
    }
}

fun StatusRuntimeException.getErrors(): List<Error> {
    val status: com.google.rpc.Status = StatusProto.fromThrowable(this) ?: return emptyList()
    return status.detailsList
        .filter { detail -> detail.`is`(BadRequest::class.java) }
        .map { detail ->
            val badRequest: BadRequest = detail.unpack(BadRequest::class.java)
            badRequest.fieldViolationsList.map { violation ->
                object : Error {
                    override fun getPath(): Optional<String> {
                        return Optional.ofNullable(violation.field)
                    }

                    override fun getMessage(): String {
                        return violation.description
                    }
                }
            }
        }
        .reduce { acc, list ->
            val mutableList = if (acc is MutableList) acc else acc.toMutableList()
            mutableList.addAll(list)
            mutableList
        }
}
