package com.epam.grpctask.client;

import com.epam.grpctask.entity.MessageServiceGrpc;

import io.grpc.Channel;

public class PingPongStub {

    private static MessageServiceGrpc.MessageServiceBlockingStub messageServiceBlockingStub;

    public static MessageServiceGrpc.MessageServiceBlockingStub getInstance(Channel channel) {
        if (messageServiceBlockingStub == null) {
            messageServiceBlockingStub = MessageServiceGrpc.newBlockingStub(channel);
        }
        return messageServiceBlockingStub;
    }
}
