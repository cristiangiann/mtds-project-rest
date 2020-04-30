package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

public class GetUsersAPI extends API {
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        return gson.toJson(modelManager.getUsers());
    }
}
