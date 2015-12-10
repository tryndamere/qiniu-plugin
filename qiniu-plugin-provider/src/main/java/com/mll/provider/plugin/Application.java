package com.mll.provider.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * Created by rocky on 2015/12/10.
 */
@SpringBootApplication(scanBasePackages = {"com.mll"})
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class , args);
        new CountDownLatch(1).await();
    }

}
