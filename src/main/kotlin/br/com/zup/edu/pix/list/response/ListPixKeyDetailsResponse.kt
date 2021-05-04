package br.com.zup.edu.pix.list.response

import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import java.util.*

data class ListPixKeyDetailsResponse(
    val pixId: UUID,
    val clientId: UUID,
    val keyType: PixKeyType,
    val keyValue: String,
    val accountType: BankAccountType,
    val createdAt: String
)
