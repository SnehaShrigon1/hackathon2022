package com.wellsfargo.apisecurity.report;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wellsfargo.apisecurity.report.model.CustomCellStyle;
import com.wellsfargo.apisecurity.report.model.ScanReportExcelRow;
import com.wellsfargo.apisecurity.report.model.StylesGenerator;

public class ScanReportGenerator {

	private StylesGenerator stylesGenerator = new StylesGenerator();

	private String[] columnNames = { "API Name", "API URL EndPoint", "Vulnerability Description", "Artifacts",
			"Severity", "Remediation", "OWASP Category" };

	public void createXlsReport(List<ScanReportExcelRow> reportRows, String reportPath) throws IOException {
		byte[] bytes = generateXlsReport(reportRows);

		FileOutputStream fos = new FileOutputStream(reportPath);
		fos.write(bytes); // writes bytes into file
		fos.close();
	}

	public byte[] generateXlsxReport(List<ScanReportExcelRow> reportRows) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();

		return generateReport(wb, reportRows);
	}

	public byte[] generateXlsReport(List<ScanReportExcelRow> reportRows) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();

		return generateReport(wb, reportRows);
	}

	private byte[] generateReport(Workbook wb, List<ScanReportExcelRow> reportRows) throws IOException {
		Map<CustomCellStyle, CellStyle> styles = stylesGenerator.prepareStyles(wb);
		Sheet sheet = wb.createSheet("Security Scan");

		setColumnsWidth(sheet);

		createHeaderRow(sheet, styles);
		for (int i = 0; i < reportRows.size(); i++) {
			createStringsRow(sheet, styles, reportRows.get(i), i + 1);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);

		out.close();
		wb.close();

		return out.toByteArray();
	}

	private void setColumnsWidth(Sheet sheet) {
		sheet.setColumnWidth(0, 256 * 20);

		for (int columnIndex = 1; columnIndex <= columnNames.length; columnIndex++) {
			sheet.setColumnWidth(columnIndex, 256 * 30);
		}
	}

	private void createHeaderRow(Sheet sheet, Map<CustomCellStyle, CellStyle> styles) {
		Row row = sheet.createRow(0);

		for (int columnNumber = 1; columnNumber <= columnNames.length; columnNumber++) {
			Cell cell = row.createCell(columnNumber);

			cell.setCellValue(columnNames[columnNumber - 1]);
			cell.setCellStyle(styles.get(CustomCellStyle.GREY_CENTERED_BOLD_ARIAL_WITH_BORDER));
		}
	}

	private void createRowLabelCell(Row row, Map<CustomCellStyle, CellStyle> styles, String label) {
		Cell rowLabel = row.createCell(0);
		rowLabel.setCellValue(label);
		rowLabel.setCellStyle(styles.get(CustomCellStyle.RED_BOLD_ARIAL_WITH_BORDER));
	}

	private void createStringsRow(Sheet sheet, Map<CustomCellStyle, CellStyle> styles, ScanReportExcelRow reportRow,
			int rowNumber) {
		Row row = sheet.createRow(rowNumber);
		createRowLabelCell(row, styles, "" + rowNumber);

		Cell cell = row.createCell(1);
		cell.setCellValue(reportRow.getApiName());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(2);
		cell.setCellValue(reportRow.getApiUrl());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(3);
		cell.setCellValue(reportRow.getVulnerability());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(4);
		cell.setCellValue(reportRow.getArtifact());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(5);
		cell.setCellValue(reportRow.getSeverity());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(6);
		cell.setCellValue(reportRow.getRemediation());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

		cell = row.createCell(7);
		cell.setCellValue(reportRow.getOwaspCategory());
		cell.setCellStyle(styles.get(CustomCellStyle.RIGHT_ALIGNED));

	}

}
