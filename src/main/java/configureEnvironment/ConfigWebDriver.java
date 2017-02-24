package configureEnvironment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;

import com.ontech.drivers.RemoteDriver;
import com.ontech.utilDriver.WebDriverFactory;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

/**
 * Initializes the properties and driver
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 */

@Listeners({ SauceOnDemandTestWatcher.class })
public class ConfigWebDriver implements SauceOnDemandSessionIdProvider,
        SauceOnDemandAuthenticationProvider {

    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(ConfigWebDriver.class);

    /******** Properties Attribute ********/
    private String environmentName;
    private int screenWidth;
    private int screenHeight;
    private String logLevel;
    private String properties;
    private String browser;
    private String version;
    private String context;
    private String language;
    private String environment;
    private String initialURL;
    private String os;
    private String takeScreenShotPath;
    private String screenshotPath;
    private String videoRecordingPath;
    private boolean takeScreenShot;
    private boolean ideEnabled;
    private String downloadPath;
    private static ConfigWebDriver instance = null;

    /******** Sauce Labs Attribute ********/
    private Object[][] remoteNavigators;
    private String buildTag;
    private String username;
    private String accesskey;
    private SauceOnDemandAuthentication authentication;
    private ThreadLocal<RemoteDriver> parallelRemoteDrivers = new ThreadLocal<RemoteDriver>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /******** Driver Attribute ********/
    private WebDriverFactory webDriverFactory;
    private WebDriver configWebDriver;

    /******** Constant Attribute ********/
    private final static String REMOTE_BROWSER = "Remote";

    /********Properties Attribute*******/
    private Properties prop;
    private HashMap<String, Properties> propsMap = new HashMap<String, Properties>();
    private ClassLoader loader;

    /**
     * Singleton pattern
     * @return a single instance
     */
    public static ConfigWebDriver getInstance() {
        if (instance == null) {
            instance = new ConfigWebDriver();
        }
        return instance;
    }

    /******** Reads properties when the class is instanced ********/
    private ConfigWebDriver() {
        this.readProperties();
    }

    /******** Read properties method ********/
    public void readProperties() {
        properties = "test.properties";
        prop = new Properties();

        log = Logger.getLogger(this.getClass());
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("[ POM Configuration ] - Read the basic properties configuration from: "
                + properties);

        try {
            loader = Thread.currentThread().getContextClassLoader();

            /******** Load a Properties File ********/
            prop.load(loader.getResourceAsStream(properties));
            
            /******** Get the Sauce Labs Property value ********/
            username = prop.getProperty("user");
            accesskey = prop.getProperty("accessKey");
            buildTag = prop.getProperty("build");
            remoteNavigators = fetchArrayFromPropFile("parallelBrowsers", prop);
            
            /******** Create the Sauce Lab Authentication ********/
            authentication = new SauceOnDemandAuthentication(username, accesskey);

            /******** Get the Properties Values.********/
            screenWidth = Integer.parseInt(prop.getProperty("screenWidth"));
            screenHeight = Integer.parseInt(prop.getProperty("screenHeight"));
            logLevel = prop.getProperty("logLevel");
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
            version = prop.getProperty("version");
            language = prop.getProperty("language");
            environment = prop.getProperty("environment");
            environmentName = prop.getProperty("environmentName");
            initialURL = environment + language;
            takeScreenShotPath = prop.getProperty("takeScreenShotPath");
            screenshotPath = prop.getProperty("screenshotPath");
            takeScreenShot = "true".equals(prop.getProperty("activateTakeScreenShot", "false"));
            ideEnabled = "true".equals(prop.getProperty("ideEnabled", "false"));

            /******** Generate the Download Path ********/
            downloadPath = "";

            String auxPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
            String[] arrayPath = auxPath.split("/");

            if (auxPath.startsWith(File.separator)) {
                downloadPath = File.separator;
            }

            for (int i = 1; i < arrayPath.length; i++) {
                downloadPath = downloadPath + arrayPath[i] + "/";
            }

            downloadPath = downloadPath.replace("target/classes/",  prop.getProperty("downloadPath"));
            downloadPath = downloadPath.replaceAll("/",  Matcher.quoteReplacement(File.separator));

            if (!downloadPath.endsWith(Matcher.quoteReplacement(File.separator))) {
                downloadPath += Matcher.quoteReplacement(File.separator);
            }

            File file = new File(this.getDownloadPath());
            if (!file.exists()) {
                file.mkdir();
            }
            
            /******** Properties Information ********/
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            Enumeration<Object> em = prop.keys();
            while (em.hasMoreElements()) {
                em.nextElement();
            }
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            
            /******** POM Information ********/
            log.info("[ POM Configuration ] - OS: " + os + " | Browser: "
                    + browser + " | Version: " + version + " | Language: "
                    + language);
            log.info("[ POM Configuration ] - Logger Level: " + logLevel);
            log.info("[ POM Configuration ] - Browser size dimension defined on: "
                    + screenWidth + "px X " + screenHeight + "px");
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        } catch (IOException ex) {
            log.error("test.properties file is not found. If this is the first time you excuted your test you can copy the settings properties file in the test folder in svn and customized it to match your environment");
        }
    }

    /******** Driver Initialization Method 
     * @throws  ********/
    public ThreadLocal<RemoteDriver> initialize(String browser,
            String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException,
            UnexpectedException {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("[ Configuration ] - Initializing driver configuration");

        webDriverFactory = new WebDriverFactory();

        if (this.browser.equalsIgnoreCase(REMOTE_BROWSER)) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            /******** Set the Configuration Capabilities for each Bowser ********/
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
            capabilities.setCapability(CapabilityType.VERSION, version);
            capabilities.setCapability(CapabilityType.PLATFORM, os);
            capabilities.setCapability("name", method.getName());
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

            if (buildTag != null) {
                capabilities.setCapability("build", buildTag);
            }
            SauceHelpers.addSauceConnectTunnelId(capabilities);

            /******** Get the Driver from Factory ********/
            RemoteDriver sauce = (RemoteDriver) webDriverFactory.getDriver(this.browser, this.os, capabilities, authentication);
            propsMap.put(sauce.getCustomWebDriveSessionID(), (Properties) prop.clone());

            log.info("[ Configuration ] - Driver type configuration is established, ID of session ["+ sauce.getCustomWebDriveSessionID() + "]");

            /******** Set the Parallel instance and the correspond session ID ********/
            parallelRemoteDrivers.set(sauce);
            sessionId.set(sauce.getCustomWebDriveSessionID());

            /******** Set the initial URL and set the screen into Full Screen Size ********/
            parallelRemoteDrivers.get().get(initialURL);
            parallelRemoteDrivers.get().sleep(2);

            /******** Resize the browser ********/
            setBrowserSize(parallelRemoteDrivers.get());
            parallelRemoteDrivers.get().sleep(2);
            getInfoBrowserSize(parallelRemoteDrivers.get());

        } else {

            configWebDriver = webDriverFactory.getDriver(this.browser, this.os, null, null);
            configWebDriver.get(initialURL);
            
            setBrowserSize(configWebDriver);
            this.cleanDownloadDirectory();
            getInfoBrowserSize(configWebDriver);
        }

        return parallelRemoteDrivers;
    }

    /******** Get Information about the Browser Size Method ********/
    public void getInfoBrowserSize(WebDriver driver) {

        log.info("[ Browser Run Size ] - Browser size dimension established on: "
                + driver.manage().window().getSize().getWidth()
                + "px X "
                + driver.manage().window().getSize().getHeight() + "px");
    }

    /******** Set Browser Size Method ********/
    public void setBrowserSize(WebDriver driver) {
    	driver.manage().window().setSize(new Dimension(screenWidth, screenHeight));
    }
    /******************************************************************************************************************************************************************
     *                 										           DOWNLOAD METHODS SECTION       																				   *
     *****************************************************************************************************************************************************************/

    /**
     * Returns the donwloaded filepath
     * @param filename the name of the file
     * @return the donwloaded filepath
     */
    public String getDownloadedFilePath(String filename) {
        String path = this.getDownloadPath() + filename;
        File f = new File(path);
        if (f.exists()) {
            return path;
        } else {
            log.info("Not exists the file");
            return null;
        }
    }

    /**
     * Clean the download directory.
     */
    public void cleanDownloadDirectory() {
        File f = new File(this.getDownloadPath());
        if (f.exists() && f.isDirectory()) {
            try {
                FileUtils.cleanDirectory(f);
            } catch (IOException e) {
                log.error(e.getStackTrace());
            }
        }
    }

    /**
     * Checks if the directory is empty.
     * @return true is it's empty
     */
    public boolean isEmptyDownloadDirectory() {
        File f = new File(this.getDownloadPath());
        return f.exists() && f.isDirectory() && f.list().length == 0;
    }

    /**
    * Creates two dimensional array from delineated string in properties file
    * @param propertyName name of the property as in the file
    * @param propFile the instance of the Properties file that has the property
    * @return two dimensional array
    */
    private static String[][] fetchArrayFromPropFile(String propertyName,
            Properties propFile) {

    		/******** Get the Array Split up by the semicolon (;) ********/
        String[] a = propFile.getProperty(propertyName).split(";");

        /******** Create the two dimensional array with the correct size ********/
        String[][] array = new String[a.length][a.length];

        /******** Combine the arrays split by semicolon and comma ********/
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i].split(",");
        }
        return array;
    }

    /******** Get Session ID (SauceLabs) Method ********/
    @Override
    public String getSessionId() {
        return sessionId.get();
    }

    /******** Get Local Driver Method ********/
    public WebDriver getLocalDriver() {
        return configWebDriver;
    }

    /******** Get Remote Driver Method ********/
    public RemoteDriver getRemoteDriver() {
        return parallelRemoteDrivers.get();
    }

    /******** Get Remote Navigators Method ********/
    public Object[][] getRemoteNavigators() {
        return remoteNavigators;
    }

    /******** Quit (tear down) a Parallel Remote Driver Method ********/
    public void tearDown() throws Exception {
        parallelRemoteDrivers.get().quit();
    }

    /******** Get Authentication on SauceLabs Method ********/
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    /******** Get Authentication User  SauceLabs Method ********/
    public String getAuthenticationUser() {
        return username;
    }

    /******** Get Authentication Key SauceLabs Method ********/
    public String getAuthenticationKey() {
        return accesskey;
    }

    /******** Get Environment Name Method ********/
    public String getEnvironmentName() {
        return environmentName;
    }

    /******** Get Log Level Method ********/
    public Level getLoggerLevel() {
        return Level.toLevel(logLevel);
    }

    /******** Get Version Method ********/
    public String getVersion() {
        return version;
    }

    /******** Get Browser Method ********/
    public String getBrowser() {
        return browser;
    }

    /******** Get Context Method ********/
    public String getContext() {
        return context;
    }

    /******** Get Language Method ********/
    public String getLanguage() {
        return language;
    }

    /******** Get Environment Method ********/
    public String getEnvironment() {
        return environment;
    }

    /******** Get Initial URL Method ********/
    public String getInitialURL() {
        return initialURL;
    }

    /******** Get O.S. Method ********/
    public String getOS() {
        return os;
    }

    /******** Get Screenshot Path Method ********/
    public String getScreenshotPath() {
        return this.screenshotPath;
    }

    /******** Get Video Recording Path Method ********/
    public String getVideoRecordingPath() {
        return videoRecordingPath;
    }

    /******** Get TakeScreenshot Path Method ********/
    public String getTakeScreenShotPath() {
        return takeScreenShotPath;
    }

    /******** Check if takeScreenshot is active ********/
    public boolean isTakeScreenShot() {
        return takeScreenShot;
    }

    public boolean isIDEEnabled() {
        return ideEnabled;
    }

    /******** Get Download Path Methos ********/
    public String getDownloadPath() {
        return downloadPath;
    }

}