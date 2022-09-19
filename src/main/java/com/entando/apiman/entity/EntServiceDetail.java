package com.entando.apiman.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EntServiceDetail")

public class EntServiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int urlId;
	private String url;
	private String serviceType;

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String Url) {
		this.url = Url;
	}

	public String getserviceType() {
		return serviceType;
	}

	public void setserviceType1(String serviceType) {
		this.serviceType = serviceType;
	}

}
