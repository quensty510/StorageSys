package com.quensty.storagesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 * @author Laiwenjun
 */
@SpringBootApplication(scanBasePackages = {"com.quensty"})
public class StorageSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageSysApplication.class, args);
    }

}
