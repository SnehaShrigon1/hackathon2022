package com.wellsfargo.apisecurity.scan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import com.wellsfargo.apisecurity.SecurityScanApplication;

public class SecutiryScanner {

	public boolean scan() throws IOException, InterruptedException {

		URL scanBatchResource = getClass().getClassLoader().getResource("securityscan.bat");
		if (scanBatchResource == null) {
			throw new IllegalArgumentException("securityscan.bat file not found!");
		} else {
			try {
				File securityScanFile = new File(scanBatchResource.toURI());
				String securityScanFilePath = securityScanFile.getAbsoluteFile().getAbsolutePath();

				URL vulnarabiliTestResource = getClass().getClassLoader().getResource("VulnerabilityTest.json");
				if (vulnarabiliTestResource == null) {
					throw new IllegalArgumentException("VulnerabilityTest.json file not found!");
				}
				File vulnarabilityTestFile = new File(vulnarabiliTestResource.toURI());
				String vulnarabilityTestFilePath = vulnarabilityTestFile.getAbsoluteFile().getAbsolutePath();

				ProcessBuilder pb = new ProcessBuilder(securityScanFilePath);
				pb.environment().put("POSTMAN_COLLECTION", vulnarabilityTestFilePath);
				pb.environment().put("POSTMAN_SCANS_DIR", SecurityScanApplication.properties.getProperty("POSTMAN_SCANS_DIR"));
				pb.environment().put("POSTMAN_SCANS_RESULTS", SecurityScanApplication.properties.getProperty("POSTMAN_SCANS_RESULTS"));

				Process p = pb.start();
				StringBuilder str = new StringBuilder();
				InputStreamReader isr = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					str.append(line + "\n");
				}
				System.out.println(str);
				int code = p.waitFor();
				System.out.println("code:"+code);
				if (code == 0) {
					System.out.println(str);
					return true;
				}

			} catch (URISyntaxException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
