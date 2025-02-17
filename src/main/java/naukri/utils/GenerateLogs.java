package naukri.utils;


import java.util.logging.Logger;

public class GenerateLogs {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(String.valueOf(GenerateLogs.class));
        log.info("launching Browser");
        log.info("Url is launched");
    }
}
