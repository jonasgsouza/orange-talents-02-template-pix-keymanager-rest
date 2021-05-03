package br.com.zup.edu.pix.find.response

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class HolderDetails(
    val id: UUID? = null,
    val name: String,
    val document: String
)