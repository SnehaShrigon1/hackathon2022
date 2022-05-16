package com.wellsfargo.apisecurity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.wellsfargo.apisecurity.report.ScanCsvReportParser;
import com.wellsfargo.apisecurity.report.ScanReportGenerator;
import com.wellsfargo.apisecurity.report.model.ScanReportExcelRow;
import com.wellsfargo.apisecurity.scan.SecutiryScanner;

public class SecurityScanApplication {

	public static final Properties properties;

	static {
		properties = new Properties();
		try (InputStream input = SecurityScanApplication.class.getClassLoader()
				.getResourceAsStream("config.properties")) {

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");

			}

			// load a properties file from class path, inside static method
			properties.load(input);

			// get the property value and print it out
			System.out.println(properties.getProperty("POSTMAN_SCANS_DIR"));
			System.out.println(properties.getProperty("POSTMAN_SCANS_RESULTS"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {

			SecutiryScanner scanner = new SecutiryScanner();
			boolean scanCompleted = scanner.scan();

			if (scanCompleted) {
				// search csv file in directory
				File reportDir = new File(properties.getProperty("POSTMAN_SCANS_RESULTS"));
				File[] scanCsvFiles = reportDir.listFiles();
				List<File> filesToBeRenamed = new ArrayList<File>();
				for (File scanCsvFile : scanCsvFiles) {
					String filename = scanCsvFile.getName();
					if (filename.endsWith(".csv")) {
						int index = filename.indexOf(".");
						String excelFileName = reportDir.getAbsolutePath() + "\\" + filename.substring(0, index)
								+ ".xls";
						List<ScanReportExcelRow> scanReportExcelRows = ScanCsvReportParser.parseCsv(scanCsvFile.getAbsolutePath());
						System.out.println("excelFileName:" + excelFileName);
						ScanReportGenerator reportGenerator = new ScanReportGenerator();
						reportGenerator.createXlsReport(scanReportExcelRows, excelFileName);
						filesToBeRenamed.add(scanCsvFile);
					}
				}

				for (File scanCsvFile : filesToBeRenamed) {
					scanCsvFile.deleteOnExit();
				}

			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
