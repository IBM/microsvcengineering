package com.ibm.dip.samplemicrosvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ibm.dip"})
public class SampleMicroSvcApp {
    public static void main(String[] args) {
        SpringApplication.run(SampleMicroSvcApp.class, args);
    }
}
