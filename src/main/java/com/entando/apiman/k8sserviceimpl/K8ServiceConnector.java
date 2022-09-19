package com.entando.apiman.k8sserviceimpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

@Component
public class K8ServiceConnector {

	public CoreV1Api getV1API() throws FileNotFoundException, IOException {

		String kubeConfigPath = "jk";
		ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

		// set the global default api-client to the in-cluster one from above

		Configuration.setDefaultApiClient(client);

		// the CoreV1Api loads default api-client from global configuration.
		return new CoreV1Api();

	}

}
