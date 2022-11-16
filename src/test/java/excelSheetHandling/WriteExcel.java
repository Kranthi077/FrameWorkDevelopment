package excelSheetHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public static void main(String[] args) throws IOException {

		String filename = "ReadAndWrite.xlsx";

		// Creates an object of File class to open xlsx file
		File file = new File(filename);

		// to read data from excel
		FileInputStream fis = new FileInputStream(file);

		// This object will read the workbook
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("data");
		int rowcount = sheet.getLastRowNum();
		System.out.println("row count is: " + rowcount);

		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowcount + 1);

		String[] input = { "Raja", "Kerala" };
		for (int i = 0; i < row.getLastCellNum(); i++) {
			// filling excel sheet

			Cell cell = newRow.createCell(i);
			cell.setCellValue(input[i]);
		}
		// close file input stream

		fis.close();

		// create an object of fos to write data in excel
		FileOutputStream fos = new FileOutputStream(file);

		workbook.write(fos);
		fos.close();
		System.out.println("completed");

	}

}
