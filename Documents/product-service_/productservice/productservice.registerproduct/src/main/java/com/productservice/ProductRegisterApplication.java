package com.productservice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import model.Produto;
import repository.ProdutoRepository;

@SpringBootApplication
@ComponentScan(basePackageClasses=ProdutoController.class)
@EntityScan(basePackageClasses=Produto.class)
@EnableJpaRepositories(basePackageClasses=ProdutoRepository.class)
@RefreshScope
@EnableEurekaClient
public class ProductRegisterApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductRegisterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
