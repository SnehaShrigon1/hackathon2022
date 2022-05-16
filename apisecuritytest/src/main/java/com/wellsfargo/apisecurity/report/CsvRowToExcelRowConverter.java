package com.wellsfargo.apisecurity.report;

import java.net.URI;
import java.net.URISyntaxException;

import com.wellsfargo.apisecurity.report.model.ScanReportCsvRow;
import com.wellsfargo.apisecurity.report.model.ScanReportExcelRow;

public class CsvRowToExcelRowConverter {

	public static ScanReportExcelRow convert(ScanReportCsvRow csvRow) throws URISyntaxException {
		ScanReportExcelRow excelRow = new ScanReportExcelRow();

		excelRow.setApiUrl(csvRow.getUrl());
		excelRow.setApiName(getDomainName(csvRow.getUrl()));
		excelRow.setVulnerability(csvRow.getExecuted());

		if (csvRow.getRequestName().contains("SQL injection")) {
			setOwaspDetails(excelRow, OWASPCategory.API8_2019);
		} else if (csvRow.getRequestName().contains("access token")) {
			setOwaspDetails(excelRow, OWASPCategory.API1_2019);
		}

		return excelRow;
	}

	private static void setOwaspDetails(ScanReportExcelRow excelRow, OWASPCategory owasp) {
		excelRow.setOwaspCategory(owasp.label);
		excelRow.setSeverity(owasp.severity);
		excelRow.setVulnerability(owasp.description);
		excelRow.setRemediation(owasp.remediation);
	}

	private static String getDomainName(String url) {
		try {
			String hostName = new URI(url).getHost();
			if (hostName != null) {
				if (!hostName.contains(".")) {
					return hostName;
				}
				String[] host = hostName.split("\\.");
				return host[host.length - 2];
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "";

	}

}
