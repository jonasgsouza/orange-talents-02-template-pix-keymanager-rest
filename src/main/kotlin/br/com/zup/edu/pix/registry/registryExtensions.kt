package br.com.zup.edu.pix.registry

import br.com.zup.edu.RegisterPixKeyResponseGrpc
import br.com.zup.edu.pix.registry.controller.response.RegisterPixKeyResponse

fun RegisterPixKeyResponseGrpc.toRegisterPixKeyResponse(): RegisterPixKeyResponse {
    return RegisterPixKeyResponse(
        pixId = pixId
    )
}