package br.com.zup.edu.pix.find

import br.com.zup.edu.FindPixKeyRequestGrpc
import br.com.zup.edu.FindPixKeyResponseGrpc
import br.com.zup.edu.FindPixKeyServiceGrpc
import br.com.zup.edu.pix.find.response.FindPixKeyResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller("/api/{clientId}/pix/{pixId}")
class FindPixKeyController(
    val grpcClient: FindPixKeyServiceGrpc.FindPixKeyServiceBlockingStub
) {

    @Get
    fun findPixKey(@PathVariable clientId: UUID, @PathVariable pixId: UUID): FindPixKeyResponse {
        val response: FindPixKeyResponseGrpc = grpcClient.findKey(
            FindPixKeyRequestGrpc.newBuilder().setPixId(
                FindPixKeyRequestGrpc.FilterByPixIdGrpc.newBuilder()
                    .setClientId(clientId.toString())
                    .setPixId(pixId.toString())
                    .build()
            ).build()
        )
        return response.toFindPixKeyResponse()
    }
}