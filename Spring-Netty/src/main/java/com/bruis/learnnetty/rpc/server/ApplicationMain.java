package com.bruis.learnnetty.rpc.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lhy
 * @date 2022/2/17
 */
@Slf4j
public class ApplicationMain {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.bruis.learnnetty.rpc");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    context.stop();
                } catch (Exception e) {
                    log.info("e: {}", e.getMessage());
                }

                synchronized (ApplicationMain.class) {
                    running = false;
                    ApplicationMain.class.notify();
                }
            }));
            context.start();
        } catch (Exception e) {
            log.info("e: {}", e.getMessage());
            System.exit(1);
        }
        System.out.println("服务器已启动");
        synchronized (ApplicationMain.class) {
            try {
                ApplicationMain.class.wait();
            } catch (Exception e) {
                log.info("e: {}", e.getMessage());
            }
        }
    }
}
