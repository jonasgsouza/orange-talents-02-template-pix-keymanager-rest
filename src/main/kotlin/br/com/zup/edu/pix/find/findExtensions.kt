package br.com.zup.edu.pix.find

import br.com.zup.edu.*
import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import br.com.zup.edu.pix.find.response.BankAccountDetails
import br.com.zup.edu.pix.find.response.FindPixKeyResponse
import br.com.zup.edu.pix.find.response.HolderDetails
import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

fun FindPixKeyResponseGrpc.toFindPixKeyResponse(): FindPixKeyResponse {
    return FindPixKeyResponse(
        pixId = UUID.fromString(pixId),
        keyType = keyType.toPixKeyType(),
        keyValue = keyValue,
        createdAt = createdAt.toLocalDateTime().toString(),
        account = account.toBankAccountDetails()
    )
}

fun KeyTypeGrpc.toPixKeyType(): PixKeyType {
    return when (this) {
        KeyTypeGrpc.CPF -> PixKeyType.CPF
        KeyTypeGrpc.EMAIL -> PixKeyType.EMAIL
        KeyTypeGrpc.PHONE -> PixKeyType.PHONE
        KeyTypeGrpc.RANDOM -> PixKeyType.RANDOM
        else -> throw IllegalArgumentException("Invalid pix key type")
    }
}

fun Timestamp.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(seconds, nanos, ZoneOffset.UTC)
}

fun BankAccountGrpc.toBankAccountDetails(): BankAccountDetails {
    return BankAccountDetails(
        institutionName = institutionName,
        agency = agency,
        number = number,
        accountType = accountType.toBankAccountType(),
        holder = holder.toHolderDetails()
    )
}

fun AccountTypeGrpc.toBankAccountType(): BankAccountType {
    return when (this) {
        AccountTypeGrpc.CONTA_POUPANCA -> BankAccountType.CONTA_POUPANCA
        AccountTypeGrpc.CONTA_CORRENTE -> BankAccountType.CONTA_CORRENTE
        AccountTypeGrpc.UNRECOGNIZED -> throw IllegalArgumentException("Tipo de conta inválido")
    }
}

fun HolderGrpc.toHolderDetails(): HolderDetails {
    return HolderDetails(
        id = UUID.fromString(id),
        name = name,
        document = document
    )
}