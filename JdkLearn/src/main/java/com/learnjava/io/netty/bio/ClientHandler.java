package com.learnjava.io.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author LuoHaiYang
 */
@Slf4j
public class ClientHandler {

    public static final int MAX_DATE_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("new client connect in...");
        new Thread(this::doStart).start();
    }

    private void doStart() {
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                byte[] data = new byte[MAX_DATE_LEN];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("client send message : " + message);
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
