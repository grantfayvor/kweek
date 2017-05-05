package com.kweek.config;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Harrison on 2017-04-13.
 */
public abstract class KweekLoggerFactory {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());
    private FileHandler fileHandler = null;

    public final Logger logger = config();

    private Logger config(){
        String separator = FileSystems.getDefault().getSeparator();
        try {
            String pathName = "logs" +separator+ "kweek_logs.log";
            fileHandler = new FileHandler(pathName, false);
        } catch (SecurityException | IOException e){
            e.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.CONFIG);
        LOGGER.addHandler(fileHandler);
        LOGGER.setLevel(Level.CONFIG);
        return LOGGER;
    }
}
