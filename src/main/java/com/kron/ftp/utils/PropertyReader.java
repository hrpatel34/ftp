/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kron.ftp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author hirenpatel
 */
public class PropertyReader {

        final static Logger logger = Logger.getLogger(PropertyReader.class);

        public static Properties getProperties(String fileName) throws IOException {
                Properties prop = new Properties();
                InputStream input = null;
                logger.debug("Reading properties file " + fileName);
                try {
                        input = PropertyReader.class.getClassLoader().getResourceAsStream(fileName);
                        //load a properties file from class path, inside static method
                        prop.load(input);
                } catch (IOException ex) {
                        logger.error("Error occurred while reading properties file " + fileName);
                        logger.error(ex);
                        throw ex;
                } finally {
                        if (input != null) {
                                try {
                                        input.close();
                                } catch (IOException e) {
                                        logger.error("Error occurred while reading properties file " + fileName);
                                        logger.error("Unable to close Input stream.");
                                        logger.error(e);
                                }
                        }
                }
                return prop;
        }

}
