package projectMTDS.controller;

import spark.Spark;

public class Initializer {
    public static void main(String[] args) {
        Spark.staticFiles.location("/web");
        ControllerManager controllerManager = new ControllerManager();
        APIManager apiManager = new APIManager();
        apiManager.start(controllerManager);
    }
}
