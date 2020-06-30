package projectMTDS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import projectMTDS.controller.API.*;
import projectMTDS.controller.API.authentication.Login;
import projectMTDS.controller.API.authentication.Logout;

import static spark.Spark.*;

public class APIManager {
    public static Logger logger = LoggerFactory.getLogger(APIManager.class);

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
