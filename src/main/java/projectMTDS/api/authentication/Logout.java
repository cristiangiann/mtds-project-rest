package projectMTDS.api.authentication;

import projectMTDS.authentication.Authenticator;
import projectMTDS.api.API;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;

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
        response.removeCookie("sessionId");
        response.status(200);
        return gson.toJson(new Message("User " + userId + " successfully logged out", "/"));
    }
}
