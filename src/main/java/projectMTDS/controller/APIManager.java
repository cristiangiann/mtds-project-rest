package projectMTDS.controller;

import projectMTDS.controller.API.*;
import projectMTDS.controller.API.authentication.Login;
import projectMTDS.controller.API.authentication.Logout;

import static projectMTDS.controller.Utils.logger;
import static spark.Spark.*;

public class APIManager {
    static void start() {
        post("/login", Login::call);
        post("/logout", Logout::call);
        path("/api", () -> {
            before("/*", (request, response) -> {
                logger.info("*** Received api call ***");
            });
            path("/images", () -> {
                get("", GetImagesAPI::call);
                get("/:id", GetImageAPI::call);
                post("", AddImageAPI::call);
                delete("/:id", DeleteImageAPI::call);
            });
            path("/users", () -> {
                get("", GetUsersAPI::call);
                post("", AddUserAPI::call);
            });
        });
    }
}
