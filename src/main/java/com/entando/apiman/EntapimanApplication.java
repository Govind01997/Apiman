package com.entando.apiman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.entando.apiman.entity.WebClientEntity;
import com.entando.apiman.k8sserviceimpl.EntServiceDetailImpl;

import io.kubernetes.client.openapi.ApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableScheduling
@EnableWebMvc
@SpringBootApplication
public class EntapimanApplication {
	@Autowired
	EntServiceDetailImpl entServiceDetailImpl = new EntServiceDetailImpl();
	@Autowired
	WebClient webClient;

	
	public void addurl() throws FileNotFoundException, IOException, ApiException {

		entServiceDetailImpl.saveUrl();

	}

	
	public Flux<WebClientEntity> getSubject() {
		return webClient.get().uri("/apiman/organizations/new/apis/").retrieve().bodyToFlux(WebClientEntity.class);
	}

	
	public List<WebClientEntity> create()
			throws FileNotFoundException, IOException, ApiException {
		//List<WebClientEntity> listApi = new ArrayList<WebClientEntity>(entServiceDetailImpl.Addresslength);

		List<WebClientEntity> listOutputApi = new ArrayList<WebClientEntity>(200);

		for (String f1 : entServiceDetailImpl.fetchK8sServices("Internal", "entando")) {
			WebClientEntity webClientEntity = new WebClientEntity();

			webClientEntity.setName(f1);
			webClientEntity.setDescription("okdone");

			Mono<WebClientEntity> outputApiMono;
			outputApiMono = entServiceDetailImpl.create(webClientEntity);
			listOutputApi.add(outputApiMono.block());
		}

		return listOutputApi;
	}
	public static void main(String[] args) {
		SpringApplication.run(EntapimanApplication.class, args);
		
		EntapimanApplication entapimanApplication = new  EntapimanApplication();
		
		try {
			entapimanApplication.create();
		} catch (IOException | ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
