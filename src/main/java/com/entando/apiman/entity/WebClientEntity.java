package com.entando.apiman.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "WebClientEntity")
public class WebClientEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String name;
	private String description;
//	private ApiDefinitionType definitionType;
//	private Boolean disableKeysStrip;
//    private EndpointContentType endpointContentType;
//    private String endpoint;
//	private String initialVersion;
//    private EndpointType endpointType;
////    @OneToMany(mappedBy = "WebClientEntity")
////    private Set<Plans> plans;
////    
//    private Boolean publicAPI;
//    private Boolean parsePayload;
//    private String definitionUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
//	public ApiDefinitionType getDefinitionType() {
//		return definitionType;
//	}
//	public void setDefinitionType(ApiDefinitionType definitionType) {
//		this.definitionType = definitionType;
//	}
//	public Boolean getDisableKeysStrip() {
//		return disableKeysStrip;
//	}
//	public void setDisableKeysStrip(Boolean disableKeysStrip) {
//		this.disableKeysStrip = disableKeysStrip;
//	}
//	public EndpointContentType getEndpointContentType() {
//		return endpointContentType;
//	}
//	public void setEndpointContentType(EndpointContentType endpointContentType) {
//		this.endpointContentType = endpointContentType;
//	}
//	public String getEndpoint() {
//		return endpoint;
//	}
//	public void setEndpoint(String endpoint) {
//		this.endpoint = endpoint;
//	}
//	public String getInitialVersion() {
//		return initialVersion;
//	}
//	public void setInitialVersion(String initialVersion) {
//		this.initialVersion = initialVersion;
//	}
//	public EndpointType getEndpointType() {
//		return endpointType;
//	}
//	public void setEndpointType(EndpointType endpointType) {
//		this.endpointType = endpointType;
//	}
////	public Set<Plans> getPlans() {
////		return plans;
////	}
////	public void setPlans(Set<Plans> plans) {
////		this.plans = plans;
////	}
//	public Boolean getPublicAPI() {
//		return publicAPI;
//	}
//	public void setPublicAPI(Boolean publicAPI) {
//		this.publicAPI = publicAPI;
//	}
//	public Boolean getParsePayload() {
//		return parsePayload;
//	}
//	public void setParsePayload(Boolean parsePayload) {
//		this.parsePayload = parsePayload;
//	}
//	public String getDefinitionUrl() {
//		return definitionUrl;
//	}
//	public void setDefinitionUrl(String definitionUrl) {
//		this.definitionUrl = definitionUrl;
//	}
	}
