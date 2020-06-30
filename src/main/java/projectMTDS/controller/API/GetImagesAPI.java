package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.controller.Authenticator;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

public class GetImagesAPI extends API{
    public static String call(Request request, Response response) {
        ModelManager modelManager = ModelManager.getInstance();
        Authenticator authenticator = Authenticator.getInstance();

        Gson gson = new Gson();
        logRequestData(request);

        String userId = authenticator.getUserFromSession(request.cookies());
        return gson.toJson(modelManager.getImagesByUser(userId));
    }
}
