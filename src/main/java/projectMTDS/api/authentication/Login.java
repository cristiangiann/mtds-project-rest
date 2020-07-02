package projectMTDS.api.authentication;

import projectMTDS.authentication.Authenticator;
import projectMTDS.api.API;
import projectMTDS.utils.Message;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.Utils.logger;

public class Login extends API {
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();

        logRequestData(request);

        String userId = getParameterFromBody(request.body(), "id");
        String password = getParameterFromBody(request.body(), "password");

        String sessionId = authenticator.login(userId, password);
        if(sessionId == null){
            response.status(401);
            return gson.toJson("User and password combination is not valid");
        }

        logger.info("User " + userId + " successfully logged in - Session id: " + sessionId);
        response.cookie("sessionId", sessionId, 36000, false, false);
        return gson.toJson(new Message("User " + userId + " successfully logged in.", "/pages/gallery.html"));
    }
}
