package com.epam.grpctask.service;

import com.epam.grpctask.client.PingPongStub;
import com.epam.grpctask.entity.MessageRequest;
import com.epam.grpctask.entity.MessageServiceGrpc;
import com.epam.grpctask.entity.MessageServiceGrpc.MessageServiceBlockingStub;

import io.grpc.Channel;

public class PingPongClientService extends MessageServiceGrpc.MessageServiceImplBase {

    private final Channel channel;

    public PingPongClientService(Channel channel) {
        this.channel = channel;
    }

    public String sendMessage(String message) {
        MessageServiceBlockingStub instance = PingPongStub.getInstance(channel);
        MessageRequest messageRequest = MessageRequest.newBuilder()
            .setMessage(message)
            .build();

        return instance.getMessage(messageRequest).getMessage();
    }
}