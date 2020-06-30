package projectMTDS.controller;

import spark.Spark;

import static spark.Spark.port;

public class Initializer {
    public static void main(String[] args) {
        Spark.staticFiles.location("/web");
        port(80);
        ControllerManager controllerManager = new ControllerManager();
        controllerManager.start();
    }
}
