package com.example.distrubutedcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DistrubutedCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistrubutedCacheApplication.class, args);
	}

}
