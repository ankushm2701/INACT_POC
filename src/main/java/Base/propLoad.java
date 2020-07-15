package Base;

import java.io.IOException;


public class propLoad extends commonComponents {
	

    public static void main(String[] args) throws IOException {
		launchBrowser(Constants.URL);
		propertyFileReader();
		
		
    	
    /*	ExcelFunctions excelCompnents = new ExcelFunctions();
    	Sheet ObjExcelSheet = 	excelCompnents.getExcelWorksheetObject(excelCompnents.getExcelWorkbookObject(), Constants.EXCEL_SHEET_NAME);
    	ArrayList<String> values =	excelCompnents.getExcelCellRowValues(excelCompnents.getExcelWorkbookObject(), ObjExcelSheet, "One");
    	Iterator<String> rowIterator = values.iterator();
    	while(rowIterator.hasNext()){
    		System.out.println(rowIterator.next());
    	}
    	*/
    
	}
}


