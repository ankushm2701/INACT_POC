package TestScript;

import Base.commonComponents;
import org.testng.annotations.Test;

public class Sample extends commonComponents {

    @Test
    public void sampleTest() {
        String url="https://spartacus.c39j2-walkersde1-d4-public.model-t.cc.commerce.ondemand.com/electronics-spa/en/USD";
        launchBrowser(url);
    }
}
