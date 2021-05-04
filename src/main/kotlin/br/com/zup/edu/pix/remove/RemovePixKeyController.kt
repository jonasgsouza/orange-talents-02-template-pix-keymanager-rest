package br.com.zup.edu.pix.remove

import br.com.zup.edu.RemovePixKeyRequestGrpc
import br.com.zup.edu.RemovePixKeyServiceGrpc
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller("/api/{clientId}/pix/{pixId}")
class RemovePixKeyController(
    val grpcClient: RemovePixKeyServiceGrpc.RemovePixKeyServiceBlockingStub
) {

    @Delete
    fun removePixKey(@PathVariable clientId: UUID, @PathVariable pixId: UUID) {
        grpcClient.removeKey(
            RemovePixKeyRequestGrpc.newBuilder()
                .setClientId(clientId.toString())
                .setPixId(pixId.toString())
                .build()
        )
    }

}