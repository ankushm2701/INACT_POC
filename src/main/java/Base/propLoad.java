package Base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propLoad {
	
	 InputStream inputStream;
	 Properties prop ;
	 
	 public void load() throws IOException {
	     try {
		     prop = new Properties();
			 String propFileName = "config.properties";
			 inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	         if(inputStream != null) {
			     prop.load(inputStream);
			 } 
	         else {
			     throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			 }
			     System.out.println(prop.getProperty("Cookies_Alert"));
		 }
		 catch (Exception e) {
		     System.out.println("Exception: " + e);
		} 
	     finally {
		     inputStream.close();
		}		
			
     }
	
	  public static void main(String[] args) throws IOException {
	     propLoad prop = new propLoad();
	     prop.load();	
	   }
}


