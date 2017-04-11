package com.selenium.configure.environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;


public class HandlerRepo {
	
	/**
	 * This class contains methods to download, unzip and delete the driver files needed to run the automated tests. 
	 * @author ffgonzalez
	 */

    private static final int BUFFER_SIZE = 4096;
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(HandlerRepo.class);
    
    private static HandlerRepo instance = null;
    
    private HandlerRepo() {    	
    }
    
    /**
     * Singleton pattern
     * @return a single instance
     */
    public static HandlerRepo getInstance() {
        if (instance == null) {
            instance = new HandlerRepo();
        }
        return instance;
    }    

    /**
     * Downloads a file from a URL
     * 
     * @param fileURL: HTTP URL of the file to be downloaded
     * @param saveDir: path of the directory to save the file
     * @throws IOException
     */
    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        int bytesRead;

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1, fileURL.length());
            }

            log.info("Content-Type = " + contentType);
           log.info("Content-Disposition = " + disposition);
           log.info("Content-Length = " + contentLength);
           log.info("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            while (( bytesRead = inputStream.read(buffer)) != -1) {
            	outputStream.write(buffer, 0, bytesRead);
            	}

            outputStream.close();
            inputStream.close();

            log.info("File downloaded");
        } else {
        	 log.info("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    /**
     * Unzip it
     * 
     * @param zipFile: input zip file
     * @param output: zip file output folder
     */
    public static void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];

        try {

            // create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            // get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            // get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                log.info("file unzip : " + newFile.getAbsoluteFile());

                // create all non exists folders
                // else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();              
                
            }

            zis.closeEntry();
            zis.close();

            log.info("Done");

        } catch (IOException e) {
        	log.error("unZipIt Error", e);
        }
    }
    
    /**
     *Delete zip
     * 
     * @param zipFile: zip file to delete
     */
    public static void deleteZip(String zipFile) {
        try {

            File file = new File(zipFile);

            if (file.delete()) {
            	 log.info(file.getName() + " is deleted!");
            } else {
            	 log.info("Delete operation is failed.");
            }

        } catch (Exception e) {
        	log.error("deleteZip Error", e);

        }
    }
}
