package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class GetImageAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        logRequestData(request);
        String username = request.queryParams("user");
        String imageId = request.params(":id");
        return gson.toJson(modelManager.getImage(username, imageId));
    }
}
