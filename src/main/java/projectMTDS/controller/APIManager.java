package projectMTDS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectMTDS.controller.API.*;
import projectMTDS.model.ModelManager;

import static spark.Spark.*;

class APIManager {
    static Logger logger = LoggerFactory.getLogger(APIManager.class);

    static void start(ModelManager modelManager) {
        path("/api", () -> {
            before("/*", (request, response) -> {
                logger.info("Received api call");
            });
            path("/images", () -> {
                get("",  (request, response) -> GetImagesAPI.call(request, response, modelManager));
                get("/:id",  (request, response) -> GetImageAPI.call(request, response, modelManager));
                post("", (request, response) -> AddImageAPI.call(request, response, modelManager));
                delete("/:id",  (request, response) ->  DeleteImageAPI.call(request, response, modelManager));
            });
            path("/users", () -> {
                get("",  (request, response) -> GetUsersAPI.call(request, response, modelManager));
                post("", (request, response) -> AddUserAPI.call(request, response, modelManager));
            });
        });
    }
}
