package com.projetotp3.comments;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class CommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsApplication.class, args);
	}

}
