package br.com.zup.edu.pix.enums

import br.com.zup.edu.AccountTypeGrpc

enum class BankAccountType {
    CONTA_CORRENTE,
    CONTA_POUPANCA;

    fun toAccountTypeGrpc(): AccountTypeGrpc {
        return when (this) {
            CONTA_CORRENTE -> AccountTypeGrpc.CONTA_CORRENTE
            CONTA_POUPANCA -> AccountTypeGrpc.CONTA_POUPANCA
        }
    }
}