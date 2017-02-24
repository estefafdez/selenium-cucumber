package configureEnvironment;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import drivers.RemoteDriver;

/**
 * Configuration of the Test Set. 
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 * @param <ScreenRecorder>
 */

public abstract class ConfigTestSet<ScreenRecorder> {
    /******** Log ********/
    Logger log = Logger.getLogger(ConfigTestSet.class);
    
    /******** Common Attribute ********/
    protected final static String REMOTE_BROWSER = "Remote";
    public static WebDriver configTestDriver;
    protected static ThreadLocal<RemoteDriver> remoteDriver;
    protected static ConfigWebDriver config = ConfigWebDriver.getInstance();
    protected ScreenRecorder screenRecorder;
    protected String tcName = "";

    private static final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
    private static final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
   
    /******** Data Provider for the Remote Browser ********/
    @DataProvider(parallel = true)
    public static Object[][] hardCodedBrowsers() {
        if (config.getBrowser().equalsIgnoreCase(REMOTE_BROWSER)) {
            return config.getRemoteNavigators();
        } else {
            return new Object[][] { { config.getBrowser(), config.getVersion(),
                    config.getOS() } };
        }
    }

    /******** Private Method ********/
    private Method method;

    /******** Set the Method  ********/
    @BeforeMethod
    public void before(Method m) {
        method = m;
    }

    /******** Get the Method********/
    public String getMethodName() {
        return method.getName();
    }

    /******** Test Running Information.********/
    @BeforeMethod
    public void nameBefore(Method method) {
        log.info("*********************************************************************************");
        log.info("[ Test Running ] - " + method.getName());
        log.info("*********************************************************************************");
    }

    /******** Initialize the instance and select between the Remote Browser or the Local ********/
    @BeforeMethod
    public void init(Object[] data) throws InvalidElementStateException,
            UnexpectedException, MalformedURLException {

    	 	/******** Remote Driver configuration ********/
        if (config.getBrowser().equalsIgnoreCase(REMOTE_BROWSER)) {
            remoteDriver = config.initialize(data[0].toString(),
                    data[1].toString(), data[2].toString(), method);
            configTestDriver = remoteDriver.get();
        }
       
        /******** Local configuration. ********/
        else {
            config.initialize(config.getBrowser(), config.getVersion(),
                    config.getOS(), method);
            configTestDriver = config.getLocalDriver();
        }
    }

    /******** Method to add/remove from the Failed Suite using the Test Status ********/
    @AfterMethod
    public void afterAllIsSaidAndDone(ITestResult result) {

        /********Close Remote Driver session ********/
        if (config.getBrowser().equalsIgnoreCase(REMOTE_BROWSER)) {
            log.info("[ Driver Status ] - Clean and close the intance of finish driver ["
                    + config.getSessionId() + "]");
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            
            /******** Delete actual parallel driver and need wait 10 seconds to avoid close the thread before it's end. ********/
            config.getRemoteDriver().sleep(10);
            config.getRemoteDriver().close();
            config.getRemoteDriver().quit();

        } else {

            if (configTestDriver != null && config.isTakeScreenShot() == true
                    && configTestDriver instanceof TakesScreenshot
                    && result.getStatus() == ITestResult.FAILURE) {
                this.takeScreenShot(result);
            }

            if (configTestDriver != null) {
                log.info("[ Driver Status ] - Clean and close the intance of finish driver");
                log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                configTestDriver.manage().deleteAllCookies();
                configTestDriver.quit();
            }
        }

    }

    /******************************************************************************************************************************************************************
     *                 										           PUBLIC METHODS                  																							   *
     *****************************************************************************************************************************************************************/
    
    /**
     * Obtains the initialization configuration
     * @return initialization instance
     */
    public ConfigWebDriver getConfig() {
        return config;
    }

    /**
     * Method to get a timestamp
     * @return string with the timestamp
     */
    public static String getTimeStamp() {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    /******************************************************************************************************************************************************************
     *                 										           PRIVATE METHODS                  																							   *
     *****************************************************************************************************************************************************************/
    
    /******** Take a Screenshot when the test Failed Method ********/
    private void takeScreenShot(ITestResult result) {
        log.info("Take a ScreenShot of the result");
        try {
            if (configTestDriver != null && (config.isTakeScreenShot() == true)
                    && configTestDriver instanceof TakesScreenshot
                    && result.getStatus() == ITestResult.FAILURE) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyyMMdd-HHmm");
                String fullFilePath = config.getTakeScreenShotPath()
                        + "/"
                        + result.getMethod().getMethodName()
                        + "_"
                        + config.getLanguage()
                        + "_"
                        + config.getEnvironmentName()
                        + "_"
                        + dateFormat.format(GregorianCalendar.getInstance()
                                .getTime());

                BufferedImage nuevaImagen = ImageIO
                        .read(((TakesScreenshot) configTestDriver)
                                .getScreenshotAs(OutputType.FILE));

                /******** Save the image on a bytes array ********/
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(nuevaImagen, "png", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();

                /******** Create a new img object to changed into jpg ********/
                Image img = Toolkit.getDefaultToolkit()
                        .createImage(imageInByte);

                PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);
                pg.grabPixels();
                int width = pg.getWidth(), height = pg.getHeight();

                DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(),
                        pg.getWidth() * pg.getHeight());
                WritableRaster raster = Raster.createPackedRaster(buffer,
                        width, height, width, RGB_MASKS, null);
                BufferedImage bi = new BufferedImage(RGB_OPAQUE, raster, false,
                        null);

                /******** Create a new file to compare the length before and after the compression. ********/
                File imagen = new File(String.format("%s.jpg", fullFilePath));
                
                /******** Create the image from img to jpg ********/
                ImageIO.write(bi, "jpg", imagen);

                log.info("Original: " + imagen.length() / 1024 + " Kb");

                /******** Compression start ********/
                ImageWriter writer = ImageIO.getImageWritersBySuffix("jpg")
                        .next();
                writer.setOutput(ImageIO
                        .createImageOutputStream(new FileOutputStream(String
                                .format("%s.jpg", fullFilePath))));

                ImageWriteParam param = writer.getDefaultWriteParam();
                
                /******** Check it the image is compressed ********/
                if (param.canWriteCompressed()) {
                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(0.25f);
                }
                writer.write(null, new IIOImage(bi, null, null), param);

                /******** Compression finish ********/
                log.info("Compressed: " + imagen.length() / 1024 + " Kb");

                /******** Taking System Screenshot ********/
                try {
                    Dimension screenSize = Toolkit.getDefaultToolkit()
                            .getScreenSize();
                    Rectangle screenRectangle = new Rectangle(screenSize);
                    Robot robot = new Robot();
                    BufferedImage image = robot
                            .createScreenCapture(screenRectangle);
                    String fullFilePath2 = fullFilePath + "_2";
                    ImageIO.write(image, "png",
                            new File(fullFilePath2 + ".png"));
                } catch (Exception e) {
                    log.error(
                            "Couldn't take the screenshot using the Java Robot", e);
                }
            }
        } catch (IOException ex) {
            log.warn("Take ScreenShot could not be stoped");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /******** Resize image method ********/
    public BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage imagenRedimensionada = new BufferedImage(newW, newH,
                bufferedImage.getType());
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return imagenRedimensionada;
    }

    /******** Driver is Remote Method ********/
    public static boolean driverIsRemote() {
        if (config.getBrowser().equalsIgnoreCase(REMOTE_BROWSER)) {
            return true;
        } else {
            return false;
        }
    }

    /******** Select Environment Method: Local or Remote  ********/
    public WebDriver selectEnviroment() {

        if (ConfigTestSet.driverIsRemote()) {
            log.info("[ Configuration Driver] - The driver selected is REMOTE");
            return config.getRemoteDriver();
        } else {
            log.info("[ Configuration Driver ] - The driver selected is LOCAL");
            return config.getLocalDriver();
        }
    }

    /******** Update Test Status Method ********/
    public void updateTestStatus(boolean passed) {

        if (ConfigTestSet.driverIsRemote()) {
            SauceREST client = new SauceREST(config.getAuthenticationUser(),
                    config.getAuthenticationKey());
            if (passed) {
                client.jobPassed(config.getSessionId());
                log.info("[ Test Status ] - The status of the execution was PASSED");
            } else {
                client.jobFailed(config.getSessionId());
                log.error("[ Test Status ] - The status of the execution was FAILED");
            }
        } else {
            if (passed) {
                log.info("[ Test Status ] - The status of the execution was PASSED");
            } else {
                log.error("[ Test Status ] - The status of the execution was FAILED");
            }
        }
    }

    /**
     * Checks that the PO is ready
     * @param pageObject page object to be used
     */
    protected void isReady(BasePageObject pageObject) {
        assertTrue("The PO " + pageObject.getClass().getName()
                + " is not ready", pageObject.isReady());
    }

    /**
     * Checks that the PO is ready with a selected language
     * @param pageObject page object to be used
     * @param lang
     */
    protected void isReady(BasePageObject pageObject, String lang) {
        assertTrue("The PO " + pageObject.getClass().getName()
                + "with the lang [" + lang.toUpperCase() + "] is not ready",
                pageObject.isReady(lang));
    }

}
