package Base;


public class Constants {

    //Web Driver paths for Windows machines
    public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir") +"/src/main/resources/chromedriver.exe" ;
    //public  static  final String URL ="https://spartacus.c39j2-walkersde1-d4-public.model-t.cc.commerce.ondemand.com/electronics-spa/en/USD";
    public  static  final String EXCEL_PATH =System.getProperty("user.dir")+ "/src/main/resources/Testdata.xlsx";
    public  static  final String FILE_PATH =System.getProperty("user.dir")+ "/src/main/resources/Data";
    public  static  final String KEY_WORDS =System.getProperty("user.dir")+ "/KeyWords.csv";
    public  static  final String EXCEL_SHEET_NAME = "Sheet1";

    public static final String URL= "url";
    public static final String Action= "action";
    public static final String Xpath= "xpath";
    public static final String Click= "click";
    public static final String InsertText= "inserttext";
    public static final String DropDown= "dropdown";
    public static final String Data= "data";

    /*enum Actions {
        ACTION,
        XPATH,
        CLICK,
        TEXTBOX,
        DATA
    }*/
}

