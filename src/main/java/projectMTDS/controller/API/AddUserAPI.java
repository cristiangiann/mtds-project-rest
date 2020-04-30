package projectMTDS.controller.API;

import com.google.gson.Gson;
import projectMTDS.model.*;
import spark.Request;
import spark.Response;

public class AddUserAPI extends API{
    public static String call(Request request, Response response, ModelManager modelManager) {
        Gson gson = new Gson();
        String body = request.body();
        String username = gson.fromJson(body, User.class).getName();

        if(!checkParameter(username)){
            response.status(400);
            return gson.toJson("User not created. Username is not valid.");
        }
        if(modelManager.existUser(username)){
            response.status(409);
            return gson.toJson("User " + username + " already exists.");
        }

        modelManager.addUser(username);
        response.status(201);
        return gson.toJson("New User added with name: " + username);
    }
}
