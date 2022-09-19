/*

package com.entando.apiman.k8sService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
@Component
public class ExternalServiceFetcher {

	@Autowired
	K8ServiceConnector kConnector;

	List<String> ClusterIP;

	String ContextPath;

	Integer Port;
	String addres;
	List<String> Address;

	
	
	public List<String> xfetchService() throws FileNotFoundException, IOException, ApiException {

		CoreV1Api api = kConnector.getV1API();

		String ns = "entando";
		String ls = "entando.org/deployment=pn-baf96d56-1f31cbd9-akhileshprajapatinv-ent-project-template";
		V1ServiceList list = api.listNamespacedService(ns, "", null, null, null, null, null, null, null, null, null);

		list.getItems().forEach(e -> {
			try {
				V1PodList ll = api.listNamespacedPod(ns, "pretty", null, null, null, null, null, null, null, null,
						null);

				ClusterIP = e.getSpec().getClusterIPs();
				// System.out.println(ClusterIP);

				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();
				});

				System.out.println(ClusterIP + ":" + Port);

//				for (V1Pod item : ll.getItems()) {
//					ContextPath = item.getMetadata().getName();
//				}

				try {

					ll.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							// Port = cd.getPorts().get(0).getHostPort().toString();
							cd.getEnv().forEach(ge -> {
//                            if(ge.getName().equals("SERVER_SERVLET_CONTEXT_PATH"))
//                            {
								ContextPath = ge.getValue();

								//System.out.println(ClusterIP + ":" + Port + "/" + ContextPath);
								addres=ClusterIP + ":" + Port + "/" + ContextPath;
								Address.add(addres);
								
//                            }

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
        //System.out.println(Address);
		return Address;

	}
}

*/