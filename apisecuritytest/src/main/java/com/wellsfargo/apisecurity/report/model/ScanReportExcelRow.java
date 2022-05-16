package com.wellsfargo.apisecurity.report.model;


public class ScanReportExcelRow {

	private String apiName = "";
	private String apiUrl = "";
	private String vulnerability = "";
	private String artifact = "";
	private String severity = "";
	private String remediation = "";
	private String owaspCategory = "";
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getVulnerability() {
		return vulnerability;
	}
	public void setVulnerability(String vulnerability) {
		this.vulnerability = vulnerability;
	}
	public String getArtifact() {
		return artifact;
	}
	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getRemediation() {
		return remediation;
	}
	public void setRemediation(String remediation) {
		this.remediation = remediation;
	}
	public String getOwaspCategory() {
		return owaspCategory;
	}
	public void setOwaspCategory(String owaspCategory) {
		this.owaspCategory = owaspCategory;
	}
	
	

}
