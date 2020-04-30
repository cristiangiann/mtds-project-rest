package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.Image;
import projectMTDS.model.ModelManager;
import spark.Request;
import spark.Response;

public class DeleteImageAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        Image image = getImageFromBody(request);

        if(emptyParameter(image.getUserId()) || emptyParameter(image.getName())){
            response.status(400);
            return gson.toJson("Image not deleted. Username or Image name are not valid.");
        }
        if(!modelManager.existUser(image.getUserId())){
            response.status(404);
            return gson.toJson("User " + image.getUserId() + " not found.");
        }
        if(!modelManager.existImage(image.getUserId(), image.getName())){
            response.status(404);
            return gson.toJson("Image " + image.getName() + " not found in the repository of " + image.getUserId());
        }

        modelManager.deleteImage(image.getUserId(), image.getName());
        response.status(200);
        return gson.toJson("Image deleted: " + image.getName() + " from repository of " + image.getUserId());
    }
}
