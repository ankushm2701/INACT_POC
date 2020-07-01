package Base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

public class propLoad {
	
    InputStream inputStream;
	Properties prop ;
	PropertiesConfiguration config = null;
	public void load() throws IOException {
		 String propFileName = "config.properties";
	     try {
	 	     config = new PropertiesConfiguration(propFileName);
	 		 } 
	     catch (ConfigurationException e)
	     {
	         e.printStackTrace();
	 	 }
	     PropertiesConfigurationLayout layout = config.getLayout();
	     Set<String> keys = layout.getKeys();
	 	for (String key: keys) {
	 	    String[] arrOfStr= layout.getConfiguration().getProperty(key).toString().split(";");
	 		for (String a : arrOfStr) 
	 	        System.out.println(a); 
	 			//break;
	 	}
    }
	
    public static void main(String[] args) throws IOException {
	    propLoad prop = new propLoad();
	    prop.load();	
	}
}


