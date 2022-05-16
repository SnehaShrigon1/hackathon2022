package com.wellsfargo.apisecurity.report.model;


public class ScanReportCsvRow {
	private String iteration = "";
	private String collectionName = "";
	private String requestName = "";
	private String method = "";
	private String url = "";
	private String status = "";
	private String code = "";
	private String responseTime = "";
	private String responseSize = "";
	private String executed = "";
	private String failed = "";
	private String skipped = "";
	public String getIteration() {
		return iteration;
	}
	public void setIteration(String iteration) {
		this.iteration = iteration;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getResponseSize() {
		return responseSize;
	}
	public void setResponseSize(String responseSize) {
		this.responseSize = responseSize;
	}
	public String getExecuted() {
		return executed;
	}
	public void setExecuted(String executed) {
		this.executed = executed;
	}
	public String getFailed() {
		return failed;
	}
	public void setFailed(String failed) {
		this.failed = failed;
	}
	public String getSkipped() {
		return skipped;
	}
	public void setSkipped(String skipped) {
		this.skipped = skipped;
	}
	
	
	
}
