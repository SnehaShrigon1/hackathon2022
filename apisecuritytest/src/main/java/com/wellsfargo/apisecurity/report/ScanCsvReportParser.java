package com.wellsfargo.apisecurity.report;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.wellsfargo.apisecurity.report.model.ScanReportCsvRow;
import com.wellsfargo.apisecurity.report.model.ScanReportExcelRow;

public class ScanCsvReportParser {

	public static List<ScanReportExcelRow> parseCsv(String csvFilePath) {
		CSVParser parser;

		FileReader Data;
		List<ScanReportExcelRow> reportRows = new ArrayList<>();

		try {
			Data = new FileReader(csvFilePath);

			parser = CSVParser.parse(Data, CSVFormat.DEFAULT);

			int rowCount = 0;

			for (CSVRecord csvRecord : parser) {
				System.out.println("csvRecord.size():" + csvRecord.size());
				if (rowCount != 0) {
					ScanReportCsvRow row = new ScanReportCsvRow();
					if (csvRecord.size() > 10) {
						row.setIteration(csvRecord.get(0));
						row.setCollectionName(csvRecord.get(1));
						row.setRequestName(csvRecord.get(2));
						row.setMethod(csvRecord.get(3));
						row.setUrl(csvRecord.get(4));
						row.setStatus(csvRecord.get(5));
						row.setCode(csvRecord.get(6));
						row.setResponseTime(csvRecord.get(7));
						row.setResponseSize(csvRecord.get(8));
						row.setFailed("Failed");
						
						reportRows.add(CsvRowToExcelRowConverter.convert(row));
					}					
				}
				rowCount++;
			}
			
			parser.close();

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return reportRows;

	}

}
