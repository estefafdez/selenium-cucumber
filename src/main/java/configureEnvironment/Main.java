package configureEnvironment;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * This class contains methods to manage the latest release of the Geckodriver, ChromeDriver and IEDriver.
 * @author ffgonzalez
 */
public class Main {
	 /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        // http://chromedriver.storage.googleapis.com/index.html    --> Chrome
        // http://selenium-release.storage.googleapis.com           --> IE
        // https://github.com/mozilla/geckodriver/releases          --> FireFox
        
        String URL_REPO = "http://selenium-release.storage.googleapis.com";
        String VERSION_DRIVER = "/3.3/";
        String NAME_DRIVER = "IEDriverServer_x64_3.3.0.zip";
        String RESOURCE_PATH = "src\\main\\resources\\";

        try {
            HandlerRepo.downloadFile(URL_REPO + VERSION_DRIVER + NAME_DRIVER, RESOURCE_PATH);
            HandlerRepo.unZipIt(RESOURCE_PATH + NAME_DRIVER, RESOURCE_PATH);
            HandlerRepo.deleteZip(RESOURCE_PATH + NAME_DRIVER);
        } catch (IOException e) {
        	log.error("manageDriver Error", e);
        }

    }

}
