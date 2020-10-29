package br.com.devmedia.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.devmedia.model" })
@EnableJpaRepositories(basePackages = { "br.com.devmedia.repository" })
@ComponentScan(basePackages = {"br.com.devmedia.controller"})
public class Application {
      public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
      }
}
