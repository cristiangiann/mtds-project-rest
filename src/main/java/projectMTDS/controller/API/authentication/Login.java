package projectMTDS.controller.API.authentication;

import com.google.gson.Gson;
import projectMTDS.controller.API.API;
import projectMTDS.controller.Authenticator;
import spark.Request;
import spark.Response;

import static projectMTDS.controller.APIManager.logger;

public class Login extends API {
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();

        Gson gson = new Gson();
        logRequestData(request);

        String userId = getParameterFromBody(request.body(), "id");
        String password =  getParameterFromBody(request.body(), "password");

        String sessionId = authenticator.login(userId, password);
        if(sessionId == null){
            response.status(401);
            return gson.toJson("User and password combination is not valid");
        }

        logger.info("User " + userId + " successfully logged in - Session id: " + sessionId);
        response.cookie("sessionId", sessionId, 36000, false, false);
        response.status(200);
        return gson.toJson("User " + userId + " successfully logged in.");
    }
}
