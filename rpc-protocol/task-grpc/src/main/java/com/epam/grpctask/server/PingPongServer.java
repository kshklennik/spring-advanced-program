package com.epam.grpctask.server;

import com.epam.grpctask.service.PingPongServerService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class PingPongServer {

    private static final Logger LOGGER = Logger.getLogger(PingPongServer.class.getName());

    private static final int SERVER_PORT = 50051;

    private Server server;

    public void startServer() throws IOException {
        server = ServerBuilder.forPort(SERVER_PORT)
            .addService(new PingPongServerService())
            .build().start();
        LOGGER.log(Level.INFO, "The server is started on port: " + SERVER_PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.log(Level.INFO, "The server is shutting down.");
            try {
                this.stopServer();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "The server shutdown.", e);
            }
        }));
    }

    public void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
