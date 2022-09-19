package com.entando.apiman.k8sserviceimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.entando.apiman.entity.EntServiceDetail;
import com.entando.apiman.entity.WebClientEntity;
import com.entando.apiman.repositories.Repo;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.entando.apiman.k8sService.EntService;
@Service
@Component

public class EntServiceDetailImpl implements EntService

{
	public int Addresslength;
	public String nameSpace = "default";

	@Autowired
	K8ServiceConnector kConnector;
	@Autowired
	WebClient webClient;
	@Autowired
	private Repo repo;
	public List<String> ClusterIP;
	public String ContextPath;
	public Integer Port;

	public List<String> fetchK8sServices(String ServiceType, String nameSpace)
			throws FileNotFoundException, IOException, ApiException {
		List<List<String>> clusterIP1 = new ArrayList<>();
		CoreV1Api api = kConnector.getV1API();
		List<String> address = new ArrayList<>();
		V1ServiceList list = api.listNamespacedService(nameSpace, "", null, null, null, null, null, null, null, null,
				null);

		list.getItems().forEach(e -> {
			try {
				V1PodList podList = api.listNamespacedPod(nameSpace, "pretty", null, null, null, null, null, null, null,
						null, null);
				ClusterIP = e.getSpec().getClusterIPs();
				try {

					clusterIP1.add(ClusterIP);

				} catch (Exception g) {
					System.out.println("no");
				}
				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();

				});
				try {

					podList.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							cd.getEnv().forEach(ge -> {

								ContextPath = ge.getValue();

								address.add(ClusterIP + ":" + Port + "/" + ContextPath);

							});
						});
						;
					});
				} catch (Exception e2) {
					System.out.println("no value");
				}

			} catch (ApiException e1) {

				e1.printStackTrace();
			}
			;
		});
		Set<String> set = new HashSet<>(address);
		address.clear();
		address.addAll(set);
		Addresslength = address.size();
		return address;
	}

	@Override
	public void saveUrl() {
		try {
			for (String f1 : this.fetchK8sServices("Internal", "entando")) {
				EntServiceDetail obj = new EntServiceDetail();
				obj.setUrl(f1);
				repo.save(obj);
			}

		} catch (IOException | ApiException e) {
			e.printStackTrace();
		}
	}

	public Mono<WebClientEntity> create(WebClientEntity empl) {

		return webClient.post().uri("/apiman/organizations/new/apis/").body(Mono.just(empl), WebClientEntity.class)
				.retrieve().bodyToMono(WebClientEntity.class).timeout(Duration.ofMillis(10_0000));

	}

}
