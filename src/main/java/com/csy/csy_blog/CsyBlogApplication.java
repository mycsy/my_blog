package com.csy.csy_blog;

import com.csy.csy_blog.utils.SnowflakeIdWorker;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = ("com.csy"))
@MapperScan("com.csy.csy_blog.dao")
public class CsyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsyBlogApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*/*").allowedOrigins("*");
			}
		};
	}
	@Bean
	public SnowflakeIdWorker getSnowflakeIdWorker() {
		return new SnowflakeIdWorker(1,3);
	}
}
