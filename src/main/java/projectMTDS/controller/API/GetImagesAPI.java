package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class GetImagesAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        logRequestData(request);
        String username = request.queryParams("user");
        return gson.toJson(modelManager.getImagesByUser(username));
    }
}
