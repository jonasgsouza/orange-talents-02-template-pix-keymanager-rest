package br.com.zup.edu.pix.enums

import br.com.zup.edu.KeyTypeGrpc

enum class PixKeyType {
    CPF,
    PHONE,
    EMAIL,
    RANDOM;

    fun toKeyTypeGrpc(): KeyTypeGrpc {
        return when (this) {
            CPF -> KeyTypeGrpc.CPF
            PHONE -> KeyTypeGrpc.CNPJ
            EMAIL -> KeyTypeGrpc.EMAIL
            RANDOM -> KeyTypeGrpc.RANDOM
        }
    }
}