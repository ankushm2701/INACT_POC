package Base;

import org.apache.commons.configuration.PropertiesConfigurationLayout;

import java.io.IOException;


public class propLoad extends commonComponents {

    public static void main(String[] args) throws IOException {
		launchBrowser(Constants.URL);
    	PropertiesConfigurationLayout layout= propertyFileReader();
    	performAction(layout);
	}
}


