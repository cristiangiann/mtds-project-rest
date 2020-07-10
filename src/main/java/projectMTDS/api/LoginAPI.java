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
            logger.info("Login error - Invalid credentials");
            response.status(401);
            return createResponseBody(relatedLinks(response.status()));
        }

        logger.info("User " + userId + " successfully logged in - Session id: " + sessionId);
        response.cookie("sessionId", sessionId, 36000, false, false);
        return createResponseBody(relatedLinks(response.status()));
    }

    static private Map<String, String> relatedLinks(int status){
        Map<String, String> linkMap = new HashMap<>();
        addSelfUrl(linkMap, LOGIN_API_URL);
        if(status != 200){
            addUsersUrl(linkMap);
            return linkMap;
        };
        addLogoutUrl(linkMap);
        addImagesUrl(linkMap);
        return linkMap;
    }
}
