package com.example.githubcicd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class GithubCicdApplication {

    @GetMapping("/welcome")
    public String welcome(){
        log.info("GithubCicdApplication welcome() called");
        return "welcome!";
    }

    public static void main(String[] args) {
        SpringApplication.run(GithubCicdApplication.class, args);
    }

}
