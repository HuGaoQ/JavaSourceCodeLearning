package com.learnjava.io.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * @author LuoHaiYang
 */
@Slf4j
public class Client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5000;

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(HOST, PORT);
        new Thread(() -> {
            System.out.println("cient start success...");
            while (true) {
                try {
                    String message = "hello world";
                    System.out.println("client send data : " + message);
                    socket.getOutputStream().write(message.getBytes());
                } catch (Exception e) {
                    System.out.println("write data error!");
                }
                sleep();
            }
        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
