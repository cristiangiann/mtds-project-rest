package projectMTDS.controller;

import static spark.Spark.get;
import static spark.Spark.path;

class APIManager {
    void start(ControllerManager controllerManager) {
        path("/api", () -> {
            path("/images", () -> {
                get("/get",  (request, response) -> controllerManager.getImage(request.queryParams("id"), request.queryParams("user")));
                get("/new",  (request, response) -> controllerManager.newImage(request.queryParams("id"), request.queryParams("user")));
                get("/delete",  (request, response) -> controllerManager.deleteImage(request.queryParams("id"), request.queryParams("user")));
                get("",  (request, response) -> controllerManager.getImages(request.queryParams("user")));
            });
            path("/users", () -> {
                get("/new",  (request, response) -> controllerManager.newUser(request.queryParams("name")));
                get("",  (request, response) -> controllerManager.getUsers());
            });
        });
    }
}
