package br.com.zup.edu.pix.registry.response

import io.micronaut.core.annotation.Introspected

@Introspected
data class RegisterPixKeyResponse(
    val pixId: String
)
