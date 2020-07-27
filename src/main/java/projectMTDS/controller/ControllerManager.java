package projectMTDS.controller;

import spark.Spark;

import java.io.File;

import static projectMTDS.controller.Config.IMAGE_FOLDER_DIRECTORY;
import static projectMTDS.utils.Utils.logger;
import static spark.Spark.port;

class ControllerManager {

    ControllerManager(){}

    void start(String[] args) {
        Spark.staticFiles.location("/web");
        int selectedPort = 80;
        if (args.length > 0){
            try {
                selectedPort = Integer.parseInt(args[0]);
            } catch (Exception e) {
                logger.info("Invalid port, wrong argument. Port 80 will be selected.");
            }
        }

        createImageFolder();
        port(selectedPort);
        APIManager.start();

    }

    private static void createImageFolder() {
        File folder = new File(IMAGE_FOLDER_DIRECTORY);
        folder.mkdirs();
        logger.info("Image folder: " + IMAGE_FOLDER_DIRECTORY);
    }
}
