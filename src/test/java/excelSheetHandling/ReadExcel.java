package excelSheetHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

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
		// create a loop to read rows
		for (int i = 0; i < rowcount + 1; i++) {

			Row row = sheet.getRow(i);
			// create a loop to read cells from each row
			for (int j = 0; j < row.getLastCellNum(); j++) {

				String cellData = row.getCell(j).getStringCellValue();

				System.out.print(cellData + "|");
			}
			System.out.println();
		}

	}

}
