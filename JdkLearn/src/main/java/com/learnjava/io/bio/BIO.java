package com.learnjava.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lhy
 * <p>
 * 在windows服务器下，可以使用telnet来合serversocket建立连接
 */
@Slf4j
public class BIO {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(666);
        System.out.println("Server started...");
        while (true) {
            System.out.println("socket accepting...");
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    byte[] bytes = new byte[1024];
                    InputStream inputStream = socket.getInputStream();
                    while (true) {
                        System.out.println("reading...");
                        int read = inputStream.read(bytes);
                        if (read != -1) {
                            System.out.println(new String(bytes, 0, read));
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        log.info("e: {}", e.getMessage());
                    }
                }
            }).start();
        }
    }
}
