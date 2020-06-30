package projectMTDS.controller.API;

import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import static projectMTDS.controller.Utils.gson;

public class GetUsersAPI extends API {
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();

        logRequestData(request);
        return gson.toJson(modelManager.getUsers());
    }
}
