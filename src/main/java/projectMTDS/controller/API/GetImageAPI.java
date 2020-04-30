package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

public class GetImageAPI {
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        String username = request.queryParams("user");
        String imageId = request.queryParams("id");
        return gson.toJson(modelManager.getImage(username, imageId));
    }
}
