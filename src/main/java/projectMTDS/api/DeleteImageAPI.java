package projectMTDS.api;

import projectMTDS.authentication.Authenticator;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

import static projectMTDS.utils.Utils.gson;

public class DeleteImageAPI extends API{
    public static String call(Request request, Response response) {
        Authenticator authenticator = Authenticator.getInstance();
        ModelManager modelManager = ModelManager.getInstance();

        logRequestData(request);
        String imageId = request.params(":id");
        String userId = authenticator.getUserFromSession(request.cookies());

        if(emptyParameter(userId) || emptyParameter(imageId)){
            response.status(400);
            return gson.toJson("Image not deleted. Username or Image name are not valid.");
        }

        if(!modelManager.existImage(userId, imageId)){
            response.status(404);
            return gson.toJson("Image " + imageId + " not found in the repository of " + userId);
        }

        modelManager.deleteImage(userId, imageId);
        response.status(200);
        return gson.toJson("Image deleted: " + imageId + " from repository of " + userId);
    }
}
