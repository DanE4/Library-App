package com.libr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@PostConstruct
	private void init() {
		try {
			Process p = Runtime.getRuntime().exec("docker start library");
			p.waitFor();

		} catch (IOException e) {
			Logger logger = Logger.getLogger(DemoApplication.class.getName());
			logger.warning("Docker not started, exception: " + e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}