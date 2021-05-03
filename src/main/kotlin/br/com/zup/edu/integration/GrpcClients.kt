package br.com.zup.edu.integration

import br.com.zup.edu.RegisterPixKeyServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClients(
    @GrpcChannel("http://localhost:50051")
    val channel: ManagedChannel
) {

    @Singleton
    fun registerPixKeyGrpcClient(): RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub {
        return RegisterPixKeyServiceGrpc.newBlockingStub(channel)
    }
}