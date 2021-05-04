package br.com.zup.edu.pix.list

import br.com.zup.edu.ListPixKeyDetailsGrpc
import br.com.zup.edu.ListPixKeyRequestGrpc
import br.com.zup.edu.ListPixKeyServiceGrpc
import br.com.zup.edu.pix.list.response.ListPixKeyDetailsResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.*

@Controller("/api/{clientId}/pix")
class ListPixKeyController(
    val grpcClient: ListPixKeyServiceGrpc.ListPixKeyServiceBlockingStub
) {

    @Get
    fun listPixKey(@PathVariable clientId: UUID): List<ListPixKeyDetailsResponse> {
        return grpcClient.findKeys(
            ListPixKeyRequestGrpc.newBuilder()
                .setClientId(clientId.toString())
                .build()
        ).pixKeysList.map(ListPixKeyDetailsGrpc::toListPixKeyDetailsResponse)
    }
}