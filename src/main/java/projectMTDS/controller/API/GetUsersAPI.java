package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class GetUsersAPI extends API {
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();

        Gson gson = new Gson();
        logRequestData(request);
        return gson.toJson(modelManager.getUsers());
    }
}
