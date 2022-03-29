package com.ibsoftware.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
//Caso queira gerar um WAR ao inves do Jar adicionar o extends SpringBootServletInitializer
public class ForumApplication /* extends SpringBootServletInitializer */ {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	//Caso queira gerar um WAR ao inves do Jar descomentar o metodo de baixo
	/* @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ForumApplication.class);
	} */
}
