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
                post("", AddImageAPI::call);
                path("/:id", () ->{
                    get("", (request, response) -> GetImageAPI.call(request, response, false));
                    delete("", DeleteImageAPI::call);
                    get("/preview", (request, response) -> GetImageAPI.call(request, response, true));
                });
            });
            path("/users", () -> {
                get("", GetUsersAPI::call);
                post("", AddUserAPI::call);
                get("/:userId", GetImagesAPI::call);
            });
        });
    }
}
