package projectMTDS.api;

import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static projectMTDS.utils.UserSnapshot.getSnapshotsFromUserList;
import static projectMTDS.utils.Utils.*;

public class GetUsersAPI extends API {
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();

        logRequestData(request);
        return createResponseBody(getSnapshotsFromUserList(modelManager.getUsers()), relatedLinks());
    }

    static private Map<String, String> relatedLinks(){
        Map<String, String> linkMap = new HashMap<>();
        addLoginUrl(linkMap);
        addUsersUrl(linkMap);
        addGetImagesByUserUrl(linkMap);
        addSelfUrl(linkMap, USERS_API);
        return linkMap;
    }
}
