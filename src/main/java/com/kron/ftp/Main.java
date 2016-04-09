/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kron.ftp;

import com.kron.ftp.utils.PropertyReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author hirenpatel
 */
public class Main {

        final static Logger logger = Logger.getLogger(Main.class);

        private Properties schedulerProps;

        //private Properties ftpProps;
        private int threadsInPool = 1;
        private int delayInSeconds = 60;

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
                // TODO code application logic here
                logger.info("Main Start");
                Main obj = new Main();
                try {
                        //obj.initialize();
                        obj.startThreadPool();
                } catch (Exception e) {

                        logger.error("Error occured, so program is terminating.");
                        logger.error(e);

                }
                //obj.startThreadPool();
                logger.info("Main End");

        }

        private void initialize() throws IOException {
                //schedulerProps
                schedulerProps = PropertyReader.getProperties("scheduler.properties");
                logger.debug("Scheduler: Properties:" + schedulerProps.entrySet());
                this.delayInSeconds = Integer.parseInt(this.schedulerProps.getProperty("connections"));
                this.threadsInPool = Integer.parseInt(this.schedulerProps.getProperty("connection.frequency"));

        }

        public void startThreadPool() {
                ScheduledThreadPoolExecutor ftpThreadPool = new ScheduledThreadPoolExecutor(this.threadsInPool);
                for (int i = 1; i < this.threadsInPool; i++) {
                        ScheduledFuture<?> future = ftpThreadPool.scheduleAtFixedRate(new Runnable() {
                                @Override
                                public void run() {

                                        logger.info("Start thread " + this.hashCode());

                                        System.out.println(this.hashCode());

                                        logger.info("End thread" + this.hashCode());
                                }
                        }, 0, this.delayInSeconds, TimeUnit.SECONDS);
                }
        }
}
