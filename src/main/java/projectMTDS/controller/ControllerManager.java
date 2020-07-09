package projectMTDS.controller;

import spark.Spark;

import static spark.Spark.port;

class ControllerManager {

    ControllerManager(){}

    void start(){
        Spark.staticFiles.location("/web");
        port(80);
        APIManager.start();
    }
}
