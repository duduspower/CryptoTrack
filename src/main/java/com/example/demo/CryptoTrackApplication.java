package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
@EnableScheduling
public class CryptoTrackApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(CryptoTrackApplication.class, args);
	}

}
