package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class AddImageAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        String body = request.body();
        Image image = gson.fromJson(body, Image.class);
        String username = image.getUserId();
        String imageName = image.getName();

        if(!checkParameter(username) || !checkParameter(imageName)){
            response.status(400);
            return gson.toJson("Image not created. Username or Image name are not valid.");
        }

        modelManager.addImage(username, imageName);
        response.status(201);
        return gson.toJson("New Image added with name: " + imageName + " into repository of " + username);
    }
}
