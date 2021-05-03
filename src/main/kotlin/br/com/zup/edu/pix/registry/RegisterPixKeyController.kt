package br.com.zup.edu.pix.registry

import br.com.zup.edu.RegisterPixKeyServiceGrpc
import br.com.zup.edu.pix.registry.request.RegisterPixKeyRequest
import br.com.zup.edu.pix.registry.response.RegisterPixKeyResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import java.util.*
import javax.validation.Valid


@Controller("api/{clientId}/pix")
@Validated
class RegisterPixKeyController(
    val grpcClient: RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub
) {

    @Post
    fun registerPixKey(@PathVariable clientId: UUID, @Body @Valid request: RegisterPixKeyRequest, httpRequest: HttpRequest<*>): HttpResponse<RegisterPixKeyResponse> {
        val response: RegisterPixKeyResponse =  grpcClient.registerKey(request.toRegisterPixKeyRequestGrpc(clientId)).toRegisterPixKeyResponse()
        return HttpResponse.created(response, HttpResponse.uri("api/${clientId}/pix/${response.pixId}"))
    }

}