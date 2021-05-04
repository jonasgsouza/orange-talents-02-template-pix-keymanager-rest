package br.com.zup.edu.integration

import br.com.zup.edu.FindPixKeyServiceGrpc
import br.com.zup.edu.ListPixKeyServiceGrpc
import br.com.zup.edu.RegisterPixKeyServiceGrpc
import br.com.zup.edu.RemovePixKeyServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClients(
    @GrpcChannel("keyManager")
    val channel: ManagedChannel
) {

    @Singleton
    fun registerPixKeyGrpcClient(): RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub =
        RegisterPixKeyServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun findPixKeyGrpcClient(): FindPixKeyServiceGrpc.FindPixKeyServiceBlockingStub =
        FindPixKeyServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun removePixKeyGrpcClient(): RemovePixKeyServiceGrpc.RemovePixKeyServiceBlockingStub =
        RemovePixKeyServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun listPixKeyGrpcClient(): ListPixKeyServiceGrpc.ListPixKeyServiceBlockingStub =
        ListPixKeyServiceGrpc.newBlockingStub(channel)
}