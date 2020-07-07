package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.Utils.HOME_PAGE_URL;
import static projectMTDS.utils.Utils.gson;

public class LogoutAPI extends API {
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
        return createResponseBody(relatedLinks());
    }

    static private Map<String, String> relatedLinks(){
        Map<String, String> linkMap = new HashMap<>();
        addUrl(linkMap, "redirect_to", HOME_PAGE_URL);
        return linkMap;
    }
}
