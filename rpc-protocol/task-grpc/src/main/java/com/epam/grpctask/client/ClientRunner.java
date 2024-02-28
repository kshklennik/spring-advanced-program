package com.epam.grpctask.client;

import com.epam.grpctask.service.PingPongClientService;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientRunner {

    private static final String SERVER_HOST = "localhost:50051";
    private static final String PING = "Ping";

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(SERVER_HOST)
            .usePlaintext().build();
        PingPongClientService pingPongClientService = new PingPongClientService(managedChannel);

        while (true) {
            System.out.println(PING);
            System.out.println(pingPongClientService.sendMessage(PING));
        }
    }
}