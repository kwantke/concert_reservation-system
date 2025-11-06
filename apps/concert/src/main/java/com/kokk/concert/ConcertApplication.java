package com.kokk.concert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.kokk.support.domain.model.base") // 엔티티 스캔
@SpringBootApplication
public class ConcertApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConcertApplication.class, args);
  }

}
