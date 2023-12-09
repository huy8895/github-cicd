package com.example.githubcicd;

import lombok.extern.slf4j.Slf4j;
import org.example.TestPackage;
import org.example.TestPackageV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class GithubCicdApplication {
    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping()
    public String welcome(){
        log.info("GithubCicdApplication welcome() called");
        return "welcome! to "+ applicationName;
    }

    public static void main(String[] args) {
        TestPackage.print();
        TestPackageV2.print();
        SpringApplication.run(GithubCicdApplication.class, args);
    }

}
