package codebeers.vista;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class OnlineStore{
    public static void main(String[] args){
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);
        GestionOS gestion = new GestionOS();
        gestion.inicio();
    }
}
