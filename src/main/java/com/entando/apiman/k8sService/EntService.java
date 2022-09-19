package com.entando.apiman.k8sService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.entando.apiman.entity.*;
import io.kubernetes.client.openapi.ApiException;

public interface EntService {

	public List<String> fetchK8sServices(String ServiceType, String nameSpace)
			throws FileNotFoundException, IOException, ApiException, ApiException;

	public void saveUrl();

}
