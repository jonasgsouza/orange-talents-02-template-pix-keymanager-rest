package br.com.zup.edu.pix.registry.controller

import br.com.zup.edu.RegisterPixKeyServiceGrpc
import br.com.zup.edu.pix.registry.controller.request.RegisterPixKeyRequest
import br.com.zup.edu.pix.registry.controller.response.RegisterPixKeyResponse
import br.com.zup.edu.pix.registry.toRegisterPixKeyResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import javax.validation.Valid


@Controller("/pix")
class RegisterPixKeyController(
    val grpcClient: RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub
) {

    @Post
    fun registerPixKey(@Body @Valid request: RegisterPixKeyRequest): RegisterPixKeyResponse {
        val response = grpcClient.registerKey(request.toRegisterPixKeyRequestGrpc())
        return response.toRegisterPixKeyResponse()
    }

}