package projectMTDS.controller;

import projectMTDS.api.*;
import projectMTDS.api.LoginAPI;
import projectMTDS.api.LogoutAPI;

import static spark.Spark.*;

public class APIManager {
    static void start() {
        post("/login", LoginAPI::call);
        post("/logout", LogoutAPI::call);
        path("/api", () -> {
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
