package br.com.zup.edu.pix.find.response

import br.com.zup.edu.pix.enums.PixKeyType
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class FindPixKeyResponse(
    val pixId: UUID? = null,
    val keyType: PixKeyType,
    val keyValue: String? = null,
    val createdAt: String,
    val account: BankAccountDetails
)

