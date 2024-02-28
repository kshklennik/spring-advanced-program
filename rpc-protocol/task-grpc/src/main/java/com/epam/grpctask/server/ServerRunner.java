package com.epam.grpctask.server;

import java.io.IOException;

public class ServerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        PingPongServer pingPongServer = new PingPongServer();
        pingPongServer.startServer();
        pingPongServer.blockUntilShutdown();
    }
}