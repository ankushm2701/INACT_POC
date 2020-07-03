package Base;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class propLoad extends commonComponents {

    public static void main(String[] args) throws IOException {
    	List<String> propertyValue = propertyFileReader();
		String url="https://spartacus.c39j2-walkersde1-d4-public.model-t.cc.commerce.ondemand.com/electronics-spa/en/USD";
	    launchBrowser(url);
	    for(int i=0; i< propertyValue.size(); i++)
		{
			xpath= StringUtils.EMPTY;
			if(propertyValue.get(i).toLowerCase().equals("click"))
			{
				xpath= propertyValue.get(i-1);
				clickButton(xpath);
			}
			else if(propertyValue.get(i).toLowerCase().equals("sendkeys"))
			{
				String value= StringUtils.EMPTY;
				xpath= propertyValue.get(i-1);
				value= propertyValue.get(i+1);
				insertText(xpath, value);
			}
		}
	}
}


