package Base;

import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class propLoad extends commonComponents {

    public static void main(String[] args) throws IOException {
	/*	launchBrowser(Constants.URL);
    	PropertiesConfigurationLayout layout= propertyFileReader();
    	performAction(layout);
    	*/
    	
    	ExcelFunctions excelCompnents = new ExcelFunctions();
    	Sheet ObjExcelSheet = 	excelCompnents.getExcelWorksheetObject(excelCompnents.getExcelWorkbookObject(), Constants.EXCEL_SHEET_NAME);
    	ArrayList<String> values =	excelCompnents.getExcelCellRowValues(excelCompnents.getExcelWorkbookObject(), ObjExcelSheet, "One");
    	Iterator<String> rowIterator = values.iterator();
    	while(rowIterator.hasNext()){
    		System.out.println(rowIterator.next());
    	}
    	
    
	}
}


