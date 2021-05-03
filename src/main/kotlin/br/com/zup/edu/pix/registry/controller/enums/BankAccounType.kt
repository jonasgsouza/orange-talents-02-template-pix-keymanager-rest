package br.com.zup.edu.pix.registry.controller.enums

import br.com.zup.edu.AccountTypeGrpc

enum class BankAccounType {
    CONTA_CORRENTE,
    CONTA_POUPANCA;

    fun toAccountTypeGrpc(): AccountTypeGrpc {
        return when (this) {
            CONTA_CORRENTE -> AccountTypeGrpc.CONTA_CORRENTE
            CONTA_POUPANCA -> AccountTypeGrpc.CONTA_POUPANCA
        }
    }
}