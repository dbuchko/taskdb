/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.taskdb;

import com.example.taskdb.domain.Customer;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mark Pollack
 */
@Component
public class SampleCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SampleCommandLineRunner.class);

	private List<DataSource> dataSources;

	private CustomerRepository repository;

	@Autowired
	public SampleCommandLineRunner(CustomerRepository customerRepository, List<DataSource> dataSources) {
		this.repository = customerRepository;
		this.dataSources = dataSources;
	}


	@Override
	public void run(String... strings) throws Exception {
		log.info("Found " + dataSources.size() + " data sources.");
		for (DataSource dataSource : dataSources) {
			PoolConfiguration poolProperties = dataSource.getPoolProperties();
			String url = poolProperties.getUrl();
			String driverClassName = poolProperties.getDriverClassName();
			String username = poolProperties.getUsername();
			String password = poolProperties.getPassword();
			log.info("DataSource info -> URL: " + url + ", driver: " + driverClassName + ", username: " + username + ", password: " + password);
		}

		log.info("Saving a few customers");

		// save a couple of customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");

	}
}
