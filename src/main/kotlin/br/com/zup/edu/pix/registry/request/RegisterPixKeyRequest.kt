package br.com.zup.edu.pix.registry.request

import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import br.com.zup.edu.shared.validation.ValidPixKey
import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.validator.constraints.EmailValidator
import java.util.*
import javax.validation.constraints.NotNull

@Introspected
@ValidPixKey
data class RegisterPixKeyRequest(
    @field:NotNull
    val keyType: PixKeyType?,

    val keyValue: String?,

    @field:NotNull
    val accountType: BankAccountType?
) {
    fun toRegisterPixKeyRequestGrpc(clientId: UUID): RegisterPixKeyRequestGrpc {
        return RegisterPixKeyRequestGrpc.newBuilder()
            .setClientId(clientId.toString())
            .setKeyType(keyType?.toKeyTypeGrpc())
            .setKeyValue(keyValue ?: "")
            .setAccountType(accountType?.toAccountTypeGrpc())
            .build()
    }

    fun isKeyValueValid(): Boolean {
        requireNotNull(keyType) { "Key type must not be null" }
        return when (keyType) {
            PixKeyType.CPF -> keyValue?.matches("^[0-9]{11}\$".toRegex()) ?: false
//            PixKeyType.CNPJ -> TODO()
            PixKeyType.PHONE -> keyValue?.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex()) ?: false
            PixKeyType.EMAIL -> EmailValidator().run {
                initialize(null)
                isValid(keyValue, null)
            }
            PixKeyType.RANDOM -> keyValue.isNullOrBlank()
        }
    }
}
