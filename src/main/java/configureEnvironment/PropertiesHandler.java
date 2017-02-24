package configureEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class to read the properties files
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 */
public class PropertiesHandler {

    /**
     * Logger class initialization.
     */
    private static final Logger log = Logger.getLogger(PropertiesHandler.class);

    /**
     * Properties cache.
     */
    private Map<String, Properties> properties;

    /**
     * Instance of the PropertiesHandler class.
     */
    private static PropertiesHandler instance = null;

    /**
     * Attribute to have control about which is the last read file.
     */
    private String lastReadFile;

    /**
     * Default constructor.
     */
    private PropertiesHandler() {
        this.properties = new HashMap<String, Properties>();
        this.lastReadFile = null;
    }

    /**
     * Returns an instance of the PropertiesHanler.
     * @return PropertiesHandler instance.
     */
    public static PropertiesHandler getInstance() {
    		/******** Singleton Pattern ********/
        if (instance == null) {
            instance = new PropertiesHandler();
        }
        return instance;
    }

    /**
     * Loads a properties file with the given filename.
     * @param filename  of the properties file to read or the path to the file.
     * @return true/false if was read successfully.
     */
    public boolean load(String filename) {
        log.debug("Reading properties from the file: " + filename);
        boolean read = false;
        
        /******** If it wasn't read previously ********/
        if (!this.properties.containsKey(filename)) {
            log.debug("There is no cached properties, reading them now from " + filename);
            this.lastReadFile = null;
            InputStream inputStream = getInputStream(filename);
            
            /******** If the file was loaded ********/
            if (inputStream != null) {
                Properties propertiesFile = new Properties();
                try {
                    log.debug("[ System File Encoding ] - "
                            + Charset.defaultCharset()
                            + " ||  [ System JVM Encoding ] - "
                            + System.getProperty("file.encoding"));
                    
                    /******** Careful: Do NOT delete or comment this line. UTF-8 is mandatory.
                    Configure Eclipse: Window > Preferences > General >
                    Content Types > Text > Java Properties File --> UTF-8.
                    Window > Preferences > General > Workspace > Text file encoding --> UTF-8. ********/
                    
                    if (Charset.defaultCharset().equals("UTF-8") || System.getProperty("file.encoding").equals( "UTF-8")) {
                        propertiesFile.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        
                        /******** Add the read file into the cache ********/
                        this.properties.put(filename, propertiesFile);
                        this.lastReadFile = filename;
                        read = true;
                        log.debug(filename + " read and cached successfully!");
                    } else {
                        throw new IOException( "[ WARNING ] File encoding has not been set, using platform encoding UTF-8");
                    }

                } catch (IOException ex) {
                    log.error("An error occured reading properties from " + filename + ": " + ex);
                }
            } else {
                log.warn("Couldn't read properties from " + filename  + ". It seems the file doesn't exist");
            }
        } else {
            read = true;
            this.lastReadFile = filename;
            log.debug("Properties were cached, so we don't have to read anything");
        }
        return read;
    }

    /**
     * Returns an InputStream from a filename or file path.
     * @param filename of the properties file to read or the path to the file.
     * @return InputStream object.
     */
    private InputStream getInputStream(String filename) {
        log.debug("Getting the inputStream for the filename " + filename);
        
        /******** Check if it's a full path ********/
        File f = new File(propertyFileAbsolutePath(filename));
        InputStream inputStream = null;
        if (f.exists() && !f.isDirectory()) {
            log.info("It's a full path, and the file exists");
            try {

                inputStream = new FileInputStream(f);
            } catch (FileNotFoundException ex) {
                log.error("The file was found BUT couldn't be loaded: " + ex);
            }
        } else {
            log.debug("Reading the file from the context");
            ClassLoader classLoader = Thread.currentThread()
                    .getContextClassLoader();
            inputStream = classLoader.getResourceAsStream(filename);

        }
        return inputStream;
    }

    private String propertyFileAbsolutePath(String filename) {
        String absoluteFileName = filename;
        log.info("[ Configuration properties ] - Checking configuration from: "
                + absoluteFileName);
        File f = new File(absoluteFileName);
        if (f.exists() && !f.isDirectory()) {
            return absoluteFileName;
        }
        return filename;
    }

    /**
     * Retrieves the value of a given property.
     * @param propertyName to read.
     * @return value of the property or null if it doesn't exist.
     */
    public String get(String propertyName) {
        String value = null;
        
        /******** Return the key just if the file was loaded previously and if the properties object exists ********/
        if (this.lastReadFile != null
                && this.properties.containsKey(this.lastReadFile)) {

            value = this.properties.get(this.lastReadFile).getProperty(
                    propertyName);

        }
        return value;
    }

    /**
     * Retrieves the value of a given property on selected pageObject.
     * @param propertyName to read.
     * @param pageObject to select.
     * @return value of the property or null if it doesn't exist.
     */
    public String get(String propertyName, String PageObjectName) {
        String value = null;
        /******** Return the key just if the file was loaded previously and if the properties object exists ********/
        if (PageObjectName != null && this.properties.containsKey(PageObjectName)) {
            		value = this.properties.get(PageObjectName).getProperty(propertyName);
        }
        return value;
    }

    /**
     * Refreshes all the loaded files reading them again. If a file doesn't
     * exist now, it will not be refreshed.
     */
    public void refreshLoadedFiles() {
        log.info("Refreshing " + this.properties.size()
                + " cached properties files");
        for (String filename : this.properties.keySet()) {
            Properties oldProperties = this.properties.get(filename);
            
            /********Removing the old Properties object to force reload it. ********/
            this.properties.remove(filename);
            
            /******** If an error occurred, we put again the old properties ********/
            if (!this.load(filename)) {
                log.error("An error occurred refreshing the " + filename
                        + " file. The old properties will be used again!");
                this.properties.put(filename, oldProperties);
            } else {
                log.info(filename + " refreshed successfully!");
            }
        }
    }

    /**
     * Refreshes a given property file.
     * @param fileName to read. If a file doesn't exist now, it will not be refreshed.
     */
    public void refreshPropertyFile(String fileName) {
        log.info("Refreshing " + fileName + " cached property file");
        Properties oldProperties = this.properties.get(fileName);
        
        /********Removing the old Properties object to force reload it.********/
        this.properties.remove(fileName);
       
        /******** If an error occurred, we put again the old properties ********/
        if (!this.load(fileName)) {
            log.error("An error occurred refreshing the " + fileName
                    + " file. The old properties will be used again!");
            this.properties.put(fileName, oldProperties);
        } else {
            log.info(fileName + " refreshed successfully!");
        }
    }

}
