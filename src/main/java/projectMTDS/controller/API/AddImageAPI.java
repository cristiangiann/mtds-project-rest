package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class AddImageAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        Image image = getImageFromBody(request);

        if(emptyParameter(image.getUserId()) || emptyParameter(image.getName())){
            response.status(400);
            return gson.toJson("Image not created. Username or Image name are not valid.");
        }

        modelManager.addImage(image.getUserId(), image.getName());
        response.status(201);
        return gson.toJson("New Image added with name: " + image.getName() + " into repository of " + image.getUserId());
    }
}
