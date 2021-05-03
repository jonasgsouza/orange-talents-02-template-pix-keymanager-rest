package br.com.zup.edu.pix.registry.controller.request

import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.pix.registry.controller.enums.BankAccounType
import br.com.zup.edu.pix.registry.controller.enums.PixKeyType
import br.com.zup.edu.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotNull

@Introspected
data class RegisterPixKeyRequest(
    @field:ValidUUID
    val clientId: String?,

    @field:NotNull
    val keyType: PixKeyType?,

    val keyValue: String?,

    @field:NotNull
    val accountType: BankAccounType?
) {
    fun toRegisterPixKeyRequestGrpc(): RegisterPixKeyRequestGrpc {
        return RegisterPixKeyRequestGrpc.newBuilder()
            .setClientId(clientId)
            .setKeyType(keyType?.toKeyTypeGrpc())
            .setKeyValue(keyValue ?: "")
            .setAccountType(accountType?.toAccountTypeGrpc())
            .build()
    }
}
