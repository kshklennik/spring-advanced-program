package com.epam.grpctask.service;

import com.epam.grpctask.entity.MessageRequest;
import com.epam.grpctask.entity.MessageResponse;
import com.epam.grpctask.entity.MessageServiceGrpc;

import io.grpc.stub.StreamObserver;

public class PingPongServerService extends MessageServiceGrpc.MessageServiceImplBase {

    private static final String RESPONSE_PONG = "Pong";
    private static final String REQUEST_PING = "Ping";

    @Override
    public void getMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        if (REQUEST_PING.equals(request.getMessage())) {
            MessageResponse messageResponse = MessageResponse.newBuilder()
                .setMessage(RESPONSE_PONG)
                .build();

            responseObserver.onNext(messageResponse);
            responseObserver.onCompleted();
        }
    }
}