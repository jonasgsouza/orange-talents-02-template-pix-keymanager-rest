package br.com.zup.edu.integration

import br.com.zup.edu.FindPixKeyServiceGrpc
import br.com.zup.edu.RegisterPixKeyServiceGrpc
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
    fun registerPixKeyGrpcClient(): RegisterPixKeyServiceGrpc.RegisterPixKeyServiceBlockingStub = RegisterPixKeyServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun findPixKeyGrpcClient(): FindPixKeyServiceGrpc.FindPixKeyServiceBlockingStub = FindPixKeyServiceGrpc.newBlockingStub(channel)
}