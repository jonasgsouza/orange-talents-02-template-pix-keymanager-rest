package br.com.zup.edu.pix.list

import br.com.zup.edu.ListPixKeyDetailsGrpc
import br.com.zup.edu.pix.find.toBankAccountType
import br.com.zup.edu.pix.find.toLocalDateTime
import br.com.zup.edu.pix.find.toPixKeyType
import br.com.zup.edu.pix.list.response.ListPixKeyDetailsResponse
import java.util.*

fun ListPixKeyDetailsGrpc.toListPixKeyDetailsResponse(): ListPixKeyDetailsResponse {
    return ListPixKeyDetailsResponse(
        pixId = UUID.fromString(pixId),
        clientId = UUID.fromString(clientId),
        keyType = keyType.toPixKeyType(),
        keyValue = keyValue,
        accountType = accountType.toBankAccountType(),
        createdAt = createdAt.toLocalDateTime().toString()
    )
}