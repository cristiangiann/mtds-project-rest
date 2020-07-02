package projectMTDS.controller;

import projectMTDS.api.*;
import projectMTDS.api.authentication.Login;
import projectMTDS.api.authentication.Logout;

import static projectMTDS.utils.Utils.logger;
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
                get("/:id", (request, response) -> GetImageAPI.call(request, response, false));
                get("/:id/preview", (request, response) -> GetImageAPI.call(request, response, true));
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
