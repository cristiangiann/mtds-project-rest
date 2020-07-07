package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.Utils.*;

public class LoginAPI extends API {
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
        return createResponseBody(relatedLinks());
    }

    static private Map<String, String> relatedLinks(){
        Map<String, String> linkMap = new HashMap<>();
        addUrl(linkMap, "redirect_to", GALLERY_URL);
        return linkMap;
    }
}
