package com.example.taskdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTask
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan("com.example.taskdb.domain")
public class TaskdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskdbApplication.class, args);
	}
}
