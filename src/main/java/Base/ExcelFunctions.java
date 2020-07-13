package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctions extends Constants {
	
	public XSSFWorkbook getExcelWorkbookObject() throws IOException {

		XSSFWorkbook ObjExcelWorkBook = null;
		//Sheet ObjExcelSheet = null;

		try {
			File file = new File(Constants.EXCEL_PATH);
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjExcelWorkBook = new XSSFWorkbook(fileInputStream);
			//ObjExcelSheet = ObjExcelWorkBook.getSheetAt(0);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return ObjExcelWorkBook;
	}
	
	public Sheet getExcelWorksheetObject(XSSFWorkbook ObjExcelWorkBook, String strSheetName) throws IOException {

		Sheet ObjExcelSheet = null;

		try {
			ObjExcelSheet = ObjExcelWorkBook.getSheet(strSheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ObjExcelSheet;
	}
	
	public ArrayList<String> getExcelCellRowValues(XSSFWorkbook ObjExcelWorkBook,Sheet objExcelSheet, String strfirstColumnValue){
		//Array List declaration		
	ArrayList<String> arrApplicationName = new ArrayList<String>();
	try{
		//Iterate for all the outlined rows in excel
	Iterator<Row> rowIterator = objExcelSheet.iterator();
		//Declare a variable for verifying the first column value of each row
	String Label = "";
	//Iterate till we have rows in Excel
	while(rowIterator.hasNext()){
		Row row = rowIterator.next();
		Iterator<Cell> cellIterator = row.cellIterator();
		//Iterate till we have values in column for the selected row
		while(cellIterator.hasNext()){
			Cell column = cellIterator.next();
			//Verify if columnValue matches the label
			if(Label.equalsIgnoreCase(strfirstColumnValue)){
				arrApplicationName.add(column.getStringCellValue());
			}
			if(Label==""){
				Label = column.getStringCellValue();
			}
		}
		Label = "";			
	}
	}catch(Exception e){
	e.printStackTrace();
	}
return arrApplicationName;
	}
	

}
