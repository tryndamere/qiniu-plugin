package com.mll.provider.plugin;

import com.mll.provider.plugin.config.AuthWrap;
import com.mll.provider.plugin.config.QiniuProperties;
import com.qiniu.util.Auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

/**
 * Created by rocky on 2015/12/10.
 */
@SpringBootApplication(scanBasePackages = {"com.mll"})
@EnableConfigurationProperties(QiniuProperties.class)
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class , args);
        new CountDownLatch(1).await();
    }

    @Bean
    public AuthWrap getAuthWrap(QiniuProperties qiniuProperties) {
        return new AuthWrap(qiniuProperties);
    }

}
