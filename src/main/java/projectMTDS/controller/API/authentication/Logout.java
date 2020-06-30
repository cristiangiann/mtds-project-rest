package projectMTDS.controller.API.authentication;

import projectMTDS.controller.API.API;
import projectMTDS.controller.Authenticator;
import spark.Request;
import spark.Response;

import static projectMTDS.controller.Utils.gson;

public class Logout extends API {
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);

        String userId = authenticator.getUserFromSession(request.cookies());
        if (userId == null){
            response.status(404);
            return gson.toJson("Invalid session");
        }

        authenticator.logout(request.cookies());
        response.status(200);
        return gson.toJson("User " + userId + " successfully logged out");
    }
}
