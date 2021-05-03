package br.com.zup.edu.shared.exception

import br.com.zup.edu.shared.exception.toHttpResponse
import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import javax.inject.Singleton

@Singleton
@Produces
@Requirements(Requires(classes = [StatusRuntimeException::class, ExceptionHandler::class]))
class StatusRuntimeExceptionHandler(
    private val errorResponseProcessor: ErrorResponseProcessor<Any>
) : ExceptionHandler<StatusRuntimeException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: StatusRuntimeException): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.status.description ?: "Unknow Error")
                .build(), exception.toHttpResponse())
    }

}