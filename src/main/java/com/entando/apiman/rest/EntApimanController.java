package com.entando.apiman.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.entando.apiman.entity.WebClientEntity;
import com.entando.apiman.k8sserviceimpl.EntServiceDetailImpl;
import io.kubernetes.client.openapi.ApiException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/etando")


public class EntApimanController {
	@Autowired
	EntServiceDetailImpl entServiceDetailImpl;
	@Autowired
	WebClient webClient;

	@GetMapping("/saveAllApis")
	public void addurl() throws FileNotFoundException, IOException, ApiException {

		entServiceDetailImpl.saveUrl();

	}

	@GetMapping("/getAllApis")
	public Flux<WebClientEntity> getSubject() {
		return webClient.get().uri("/apiman/organizations/new/apis/").retrieve().bodyToFlux(WebClientEntity.class);
	}

	@PostMapping("/apiSave")
	public List<WebClientEntity> create(@RequestBody List<WebClientEntity> listApi)
			throws FileNotFoundException, IOException, ApiException {
		listApi = new ArrayList<WebClientEntity>(entServiceDetailImpl.Addresslength);

		List<WebClientEntity> listOutputApi = new ArrayList<WebClientEntity>(entServiceDetailImpl.Addresslength);

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

}
