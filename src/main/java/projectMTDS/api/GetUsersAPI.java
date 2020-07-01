package projectMTDS.api;

import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;
import static projectMTDS.utils.UserSnapshot.getSnapshotsFromUserList;

public class GetUsersAPI extends API {
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();

        logRequestData(request);
        return gson.toJson(getSnapshotsFromUserList(modelManager.getUsers()));
    }
}
