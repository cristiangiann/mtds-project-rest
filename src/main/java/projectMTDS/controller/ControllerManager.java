package projectMTDS.controller;

import projectMTDS.model.ModelManager;

class ControllerManager {
    private ModelManager modelManager;

    ControllerManager(){
        modelManager = new ModelManager();
    }

    void start(){
        APIManager.start(modelManager);
    }
}
